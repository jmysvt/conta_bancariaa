package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.TaxaDTO;
import com.senai.conta_bancaria.Domain.Entity.Taxa;
import com.senai.conta_bancaria.Domain.Repository.TaxaRepository;
import com.senai.conta_bancaria.Domain.exception.TaxaInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxaService {

    private final TaxaRepository taxaRepository;

    public Taxa crete(TaxaDTO dto){
        Taxa taxa = new Taxa();
        taxa.setDescricao(dto.descricao());
        taxa.setPercentual(dto.percentual());
        taxa.setValorFixo(dto.valorFixo());

        return taxaRepository.save(taxa);
    }

    public List<Taxa> listAll(){return taxaRepository.findAll(); }

    public Taxa findById(String id) {
        return taxaRepository.findById(id).orElseThrow(TaxaInvalidaException::new);
    }

    public void deletar(String id){taxaRepository.deleteById(id);}


}
