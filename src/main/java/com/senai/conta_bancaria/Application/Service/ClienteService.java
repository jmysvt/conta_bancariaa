package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ClienteAtualizarDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteRegistroDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Domain.Entity.Cliente;
import com.senai.conta_bancaria.Domain.Repository.ClienteRepository;
import com.senai.conta_bancaria.Domain.exception.ContaMesmoTipo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClienteResponseDTO registrarCliente (ClienteRegistroDTO dto){

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity()));

        var contas = cliente.getContas();

        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream()
                .anyMatch(c -> c.getClass().equals(novaConta.getClass()) && c.isAtiva());
        if (jaTemTipo)
            throw new ContaMesmoTipo();

        cliente.getContas().add(novaConta);
        cliente.setSenha(passwordEncoder.encode(dto.senha()));

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public List<ClienteResponseDTO> listarClientesAtivos(){
        return repository.findAllByAtivoTrue().stream()
                .map(ClienteResponseDTO::fromEntity).toList();
    }

    public ClienteResponseDTO buscarClientesCpfAtivo(String cpf){
        var cliente = buscarClientePorCpfEAtivo(cpf);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    private Cliente buscarClientePorCpfEAtivo(String cpf) {
        var cliente = getCliente(cpf);
        return cliente;
    }

    private Cliente getCliente(String cpf) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado...")
        );
        return cliente;
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteAtualizarDTO dto) {
        var cliente = buscarClientePorCpfEAtivo(cpf);

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public void deletarCliente(String cpf) {
        var cliente = buscarClientePorCpfEAtivo(cpf);
        cliente.setAtivo(false);
        cliente.getContas().forEach(
                conta -> conta.setAtiva(false)
        );

        repository.save(cliente);
    }
}
