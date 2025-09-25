package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ClienteAtualizarDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteRegistroDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Domain.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ClienteResponseDTO> listarClientesAtivos(){
        return repository.findAllByAtivoTrue().stream()
                .map(ClienteResponseDTO::fromEntity).toList();
    }

    public ClienteResponseDTO buscarClientesCpfAtivo(String cpf){
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado...")
        );
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteAtualizarDTO dto) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado...")
        );

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public void deletarCliente(String cpf) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado...")
        );
        cliente.setAtivo(false);
        repository.save(cliente);
    }
}
