package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Domain.Entity.Pagamento;
import com.senai.conta_bancaria.Domain.Entity.Taxa;
import com.senai.conta_bancaria.Domain.Repository.PagamentoRepository;
import com.senai.conta_bancaria.Domain.enums.StatusPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;

@RequiredArgsConstructor
@Service

public class PagamentoAppService {
    private final PagamentoDomainService pagamentoDomainService;
    private final PagamentoRepository pagamentoRepository;

    public void processarPagamento(Pagamento pagamento, Set<Taxa> taxas) {
        // Associando as taxas ao pagamento
        pagamento.setTaxas(taxas);

        try {
            // Processa o pagamento
            pagamentoDomainService.processarPagamento(pagamento);
        } catch (IllegalStateException | IllegalArgumentException e) {
            // Caso haja erro (como saldo insuficiente ou boleto vencido), atualizamos o status para FALHA
            pagamento.setStatus(StatusPagamento.FALHA);
            pagamentoRepository.save(pagamento);  // Salva o pagamento com o status atualizado
            throw new RuntimeException("Erro ao processar o pagamento: " + e.getMessage());
        }

        // Salva o pagamento após o processamento com status SUCESSO
        pagamentoRepository.save(pagamento);
    }
}
