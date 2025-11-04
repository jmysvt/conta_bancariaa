package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Domain.Entity.Pagamento;
import com.senai.conta_bancaria.Domain.Entity.Taxa;
import com.senai.conta_bancaria.Domain.enums.StatusPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PagamentoDomainService {
    //calculo das taxas e validações

    // Metodo para calcular as taxas do pagamento
    public BigDecimal calcularTaxas(Pagamento pagamento) {
        BigDecimal totalTaxas = BigDecimal.ZERO;

        // Calculando todas as taxas associadas ao pagamento
        for (Taxa taxa : pagamento.getTaxas()) {
            // Verifica se a taxa tem valor fixo
            if (taxa.getValorFixo() != null) {
                totalTaxas = totalTaxas.add(taxa.getValorFixo());
            }

            // Verifica se a taxa tem valor percentual
            if (taxa.getPercentual() != null) {
                totalTaxas = totalTaxas.add(pagamento.getValorPago().multiply(taxa.getPercentual()).divide(BigDecimal.valueOf(100)));
            }
        }

        return totalTaxas;
    }

    // Metodo de validação do pagamento
    public void validarPagamento(Pagamento pagamento) throws IllegalArgumentException {
        // Verifica se o pagamento já está concluído ou cancelado
        if (pagamento.getStatus() != StatusPagamento.FALHA) {
            throw new IllegalArgumentException("O pagamento não está pendente.");
        }

        // Verifica se o valor pago é maior que zero
        if (pagamento.getValorPago().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }

        // Verifica se o pagamento tem pelo menos uma taxa associada
        if (pagamento.getTaxas().isEmpty()) {
            throw new IllegalArgumentException("O pagamento deve ter pelo menos uma taxa associada.");
        }

        // Verifica se o boleto está vencido
        if (isBoletoVencido(pagamento.getDataPagamento())) {
            throw new IllegalArgumentException("O boleto está vencido e não pode ser pago.");
        }
    }

    // Metodo para verificar se o boleto está vencido
    private boolean isBoletoVencido(String dataPagamento) {
        LocalDate dataVencimento = LocalDate.parse(dataPagamento, DateTimeFormatter.ISO_DATE);
        return dataVencimento.isBefore(LocalDate.now());
    }

    // Metodo que processa o pagamento, incluindo validações e débito na conta
    public void processarPagamento(Pagamento pagamento) {
        BigDecimal totalTaxas = calcularTaxas(pagamento);
        BigDecimal valorTotal = pagamento.getValorPago().add(totalTaxas);

        // Validação do pagamento (boletos vencidos, saldo, etc)
        validarPagamento(pagamento);

        // Verificando o saldo da conta
        if (!pagamento.getConta().temSaldoSuficiente(valorTotal)) {
            pagamento.setStatus(StatusPagamento.SALDO_INSUFICIENTE);
            throw new IllegalStateException("Saldo insuficiente para realizar o pagamento.");
        }

        // Debitando o valor total da conta
        pagamento.getConta().debitarSaldo(valorTotal);

        // Atualizando o status do pagamento para concluído
        pagamento.setStatus(StatusPagamento.SUCESSO);
    }
}
