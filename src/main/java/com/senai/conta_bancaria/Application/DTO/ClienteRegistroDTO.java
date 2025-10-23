package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.Cliente;
import com.senai.conta_bancaria.Domain.Entity.Conta;
import com.senai.conta_bancaria.Domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotBlank(message = "O campo nome não pode estar em branco")
        @Schema(description = "Nome para o registro", example = "Amanda")
        String nome,

        @Schema(description = "Cpf para registro", example = "12345678900")
        @NotBlank(message = "O campo cpf não pode estar em branco")
        String cpf,

        @Schema(description = "Senha para registro", example = "123456")
        @NotBlank(message = "O campo senha não pode estar em branco")
        String senha,

        @Schema(description = "E-mail para registro", example = "exemplo@gmail.com")
        @NotBlank(message = "O campo email não pode estar em branco")
        String email,

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
                .email(this.email)
                .role(Role.CLIENTE)
                .contas(new ArrayList<Conta>())
                .build();
    };
}
