package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.Gerente;
import com.senai.conta_bancaria.Domain.enums.Role;

public record GerenteDTO(
        String id,
        String nome,
        String cpf,
        String email,
        String senha,
        Boolean ativo,
        Role role
) {
    public static GerenteDTO fromEntity(Gerente gerente) {
        return new GerenteDTO(
                gerente.getId(),
                gerente.getNome(),
                gerente.getCpf(),
                gerente.getEmail(),
                null, // senha não traz no DTO por segurança, pode ajustar se quiser
                gerente.isAtivo(),
                gerente.getRole()
        );
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

