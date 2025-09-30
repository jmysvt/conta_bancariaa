package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Application.DTO.ContaAtualizadaDTO;
import com.senai.conta_bancaria.Application.DTO.ContaResumoDTO;
import com.senai.conta_bancaria.Application.DTO.ValorSaqueDepositoDTO;
import com.senai.conta_bancaria.Domain.Entity.Conta;
import com.senai.conta_bancaria.Domain.Entity.ContaCorrente;
import com.senai.conta_bancaria.Domain.Entity.ContaPoupanca;
import com.senai.conta_bancaria.Domain.Repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ContaService {

    private final ContaRepository repository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarTodasAsContas (){
        return repository.findAllByAtivaTrue().
                stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }
    @Transactional(readOnly = true)
    public ContaResumoDTO buscarNumeroAtiva(String numero) {
        var conta = repository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("A conta não foi encontrada...")
        );
        return ContaResumoDTO.fromEntity(conta);
    }

    @Transactional
    public ContaResumoDTO atualizarNumeroConta(String numero, ContaAtualizadaDTO dto) {
        var conta = repository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("A conta não foi encontrada...")
        );

        if (conta instanceof ContaPoupanca poupanca){
            poupanca.setRendimento(dto.rendimento());
        } else if (conta instanceof ContaCorrente corrente) {
            corrente.setLimite(dto.limite());
            corrente.setTaxa(dto.taxa());
        } else{
            throw new RuntimeException("Tipo de conta inválida");
        }

        conta.setSaldo(dto.saldo());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public void deletarConta(String numero) {
        var conta = repository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("A conta não existe...")
        );

        conta.setAtiva(false);
        repository.save(conta);


    }

    public ContaResumoDTO sacar(String numero, ValorSaqueDepositoDTO dto) {
        Conta conta = repository.findByNumeroAndAtivaTrue(numero).orElseThrow(
                () -> new RuntimeException("A conta não existe...")
        );
        conta.sacar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }
}
