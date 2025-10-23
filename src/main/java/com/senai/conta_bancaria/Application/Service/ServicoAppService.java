package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ServicoDTO;
import com.senai.conta_bancaria.Domain.Entity.Servico;
import com.senai.conta_bancaria.Domain.Repository.ServicoRepository;
import com.senai.conta_bancaria.Domain.exception.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoAppService {

    private final ServicoRepository repository;

    public ServicoAppService(ServicoRepository repository) {
        this.repository = repository;
    }

    public ServicoDTO salvar(ServicoDTO dto) {
        Servico servico = dto.toEntity();
        servico.validar();
        return ServicoDTO.fromEntity(repository.save(servico));
    }

    public List<ServicoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(ServicoDTO::fromEntity)
                .toList();
    }

    public ServicoDTO buscarPorId(Long id) {
        return ServicoDTO.fromEntity(
                repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço com ID " + id + " não encontrado."))
        );
    }

    public ServicoDTO atualizar(Long id, ServicoDTO dtoAtualizado) {
        Servico existente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço com ID " + id + " não encontrado."));

        Servico atualizado = dtoAtualizado.toEntity();
        atualizado.setId(existente.getId());

        atualizado.validar();
        return ServicoDTO.fromEntity(repository.save(atualizado));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Serviço com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }

}
