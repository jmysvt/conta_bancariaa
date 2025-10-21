package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.Cliente;
import com.senai.conta_bancaria.Domain.Entity.Conta;
import com.senai.conta_bancaria.Domain.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotBlank(message = "O campo nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O campo cpf não pode estar em branco")
        String cpf,
        @NotBlank(message = "O campo senha não pode estar em branco")
        String senha,
        Role role,
        @Valid
        @NotNull
        ContaResumoDTO contaDTO

){
    public Cliente toEntity (){
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .senha(this.senha)
                .role(this.role)
                .contas(new ArrayList<Conta>())
                .build();
    };
}
