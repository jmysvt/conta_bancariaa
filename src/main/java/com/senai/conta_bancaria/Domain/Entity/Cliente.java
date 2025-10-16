package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table ( name="cliente",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "cpf")
} )


public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 120)
    @NotBlank (message = "O campo, NOME, NÃO pode estar vazio")
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull (message = "O campo, CPF, NÃO pode estar vazio")
    private  String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @NotNull(message = "O campo contas não pode estar vazio")
    private List<Conta> contas;

    @Column(nullable = false)
    private boolean ativo;

}
