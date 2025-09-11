package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cliente {

    @NotBlank (message = "O campo, NOME, NÃO pode estar vazio")
    private String nome;

    @NotNull (message = "O campo, CPF, NÃO pode estar vazio")
    private  Long cpf;

    @ElementCollection

    @ManyToOne
    @NotNull(message = "O campo contas não pode estar vazio")
    @JoinColumn(name = "numero_conta")
    private List<Conta> contas;

}
