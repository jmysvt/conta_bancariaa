package com.senai.conta_bancaria.Interface_UI.Controller;

import com.senai.conta_bancaria.Application.DTO.PagamentoDTO;
import com.senai.conta_bancaria.Application.Service.PagamentoAppService;
import com.senai.conta_bancaria.Domain.Entity.Pagamento;
import com.senai.conta_bancaria.Domain.Entity.Taxa;
import com.senai.conta_bancaria.Domain.Repository.ContaRepository;
import com.senai.conta_bancaria.Domain.Repository.TaxaRepository;
import com.senai.conta_bancaria.Domain.exception.PagamentoInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoAppService pagamentoAppService;
    private final TaxaRepository taxaRepository;
    private final ContaRepository contaRepository;

    @PostMapping("/{id}")
    public ResponseEntity<String> processarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        try {
            // Busca a conta pelo número
            var contaOpt = contaRepository.findByNumeroAndAtivaTrue(pagamentoDTO.contaNumero());
            if (contaOpt.isEmpty()) {
                return new ResponseEntity<>("Conta não encontrada ou inativa.", HttpStatus.NOT_FOUND);
            }

            var conta = contaOpt.get();


            // Busca as taxas pelos IDs fornecidos
            Set<Taxa> taxas = new HashSet<>();
            for (String taxaId : pagamentoDTO.idsTaxas()) {
                Taxa taxa = taxaRepository.findById(taxaId)
                        .orElseThrow(() -> new RuntimeException("Taxa não encontrada: " + taxaId));
                taxas.add(taxa);
            }

            // Cria o pagamento a partir do DTO
            Pagamento pagamento = new Pagamento();
            pagamento.setConta(conta);
            pagamento.setValorPago(pagamentoDTO.valor());
            pagamento.setDataPagamento(pagamentoDTO.dataPagamento());

            // Processa o pagamento pelo serviço de aplicação
            pagamentoAppService.processarPagamento(pagamento, taxas);

            return new ResponseEntity<>("Pagamento realizado com sucesso. Status: " + pagamento.getStatus(), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new PagamentoInvalidoException();
        }



    }

}

