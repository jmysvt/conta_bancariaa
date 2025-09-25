package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ContaResumoDTO;
import com.senai.conta_bancaria.Domain.Repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContaService {

    private final ContaRepository repository;

    public List<ContaResumoDTO> listarTodasAsContas (){
        return repository.findAllByAtivaTrue().
                stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

}
