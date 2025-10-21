package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.*;
import com.senai.conta_bancaria.Domain.Entity.Conta;
import com.senai.conta_bancaria.Domain.Entity.ContaCorrente;
import com.senai.conta_bancaria.Domain.Entity.ContaPoupanca;
import com.senai.conta_bancaria.Domain.Repository.ContaRepository;
import com.senai.conta_bancaria.Domain.exception.EntidadeNaoEncontradaException;
import com.senai.conta_bancaria.Domain.exception.RendimentoInvalidoException;
import com.senai.conta_bancaria.Domain.exception.TipoDeContaInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ContaService {

    private final ContaRepository repository;
    @PreAuthorize("hasAnyRole('CLIENTE')")
    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarTodasAsContas() {
        return repository.findAllByAtivaTrue().
                stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO buscarNumeroAtiva(String numero) {
        var conta = buscarContaAtivaPorNumero(numero);
        return ContaResumoDTO.fromEntity(conta);
    }

    @Transactional
    public ContaResumoDTO atualizarNumeroConta(String numero, ContaAtualizadaDTO dto) {
        var conta = buscarContaAtivaPorNumero(numero);

        conta.setSaldo(dto.saldo());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public void deletarConta(String numero) {
        Conta conta = buscarContaAtivaPorNumero(numero);

        conta.setAtiva(false);
        repository.save(conta);


    }

    public ContaResumoDTO sacar(String numero, ValorSaqueDepositoDTO dto) {
        Conta conta = buscarContaAtivaPorNumero(numero);
        conta.sacar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public ContaResumoDTO depositar(String numero, ValorSaqueDepositoDTO dto) {
        Conta conta = buscarContaAtivaPorNumero(numero);
        conta.depositar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    private Conta buscarContaAtivaPorNumero(String numero) {
        Conta conta = repository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("A conta não existe..."));
        return repository.findByNumeroAndAtivaTrue(numero).orElseThrow(()-> new EntidadeNaoEncontradaException("Conta"));
    }

    public ContaResumoDTO transferir(String numero, TransferenciaDTO dto) {
        Conta contaOrigem = buscarContaAtivaPorNumero(numero);
        Conta contaDestino = buscarContaAtivaPorNumero(dto.contaDestino());

        repository.save(contaDestino);
        return ContaResumoDTO.fromEntity(repository.save(contaOrigem));

    }


    public ContaResumoDTO aplicarRendimento(String numero) {
        Conta conta = buscarContaAtivaPorNumero(numero);
        if (conta instanceof ContaPoupanca contaPoupanca){
            contaPoupanca.aplicarRendimento();
            return  ContaResumoDTO.fromEntity(repository.save(contaPoupanca));
        }
        throw new RendimentoInvalidoException();
    }
}