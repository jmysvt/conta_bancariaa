package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ClienteRegistroDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Domain.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository repository;

    public ClienteResponseDTO registrarCliente (ClienteRegistroDTO dto){

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity()));

        var contas = cliente.getContas();

        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream()
                .anyMatch(c -> c.getClass().equals(novaConta.getClass()) && c.isAtiva());
        if (jaTemTipo)
            throw new RuntimeException("Cliente já possui uma conta dese tipo!");

        cliente.getContas().add(novaConta);

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }
}
