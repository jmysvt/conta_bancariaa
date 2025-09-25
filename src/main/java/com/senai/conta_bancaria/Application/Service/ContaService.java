package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Application.DTO.ContaResumoDTO;
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
}
