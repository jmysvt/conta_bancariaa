package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.Cliente;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ClienteResponseDTO (
    @NotBlank(message = "O campo nome não pode estar em branco")
    String nome,

    @NotBlank(message = "O campo cpf não pode estar em branco")
    String cpf,

    String id,



    List<ContaResumoDTO> contas
){

    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        List<ContaResumoDTO> contas = cliente.getContas().stream().map(ContaResumoDTO::fromEntity).toList();
        return new ClienteResponseDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getId(),
                contas
        );
    }
}
