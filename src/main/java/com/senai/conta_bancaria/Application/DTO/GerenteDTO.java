package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.Gerente;
import com.senai.conta_bancaria.Domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record GerenteDTO(
        String id,

        @NotBlank(message = "O campo nome não pode estar em branco")
        @Schema(description = "Nome para o registro", example = "Amanda")
        String nome,

        @Schema(description = "Cpf para registro", example = "12345678900")
        @NotBlank(message = "O campo cpf não pode estar em branco")
        String cpf,

        @Schema(description = "E-mail para registro", example = "exemplo@gmail.com")
        @NotBlank(message = "O campo email não pode estar em branco")
        String email,

        @Schema(description = "Senha para registro", example = "123456")
        @NotBlank(message = "O campo senha não pode estar em branco")
        String senha,

        Boolean ativo,

        Role role
) {
    public static GerenteDTO fromEntity(Gerente gerente) {
        return  GerenteDTO.builder()
                .id(gerente.getId())
                .nome(gerente.getNome())
                .cpf(gerente.getCpf())
                .email(gerente.getEmail())
                .senha(gerente.getSenha())
                .ativo(gerente.isAtivo())
                .role(gerente.getRole())
                .build();
    }

    public Gerente toEntity() {
        return Gerente.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .ativo(this.ativo != null ? this.ativo : true)
                .role(this.role != null ? this.role : Role.GERENTE)
                .build();
    }
}

