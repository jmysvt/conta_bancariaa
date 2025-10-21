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


public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @NotNull(message = "O campo contas não pode estar vazio")
    private List<Conta> contas;

    @Column(nullable = false)
    private boolean ativo;

}
