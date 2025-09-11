package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public abstract class Conta {

    @NotNull(message = "O campo NUMERO da conta NÃO pode estar vazio")
    @GeneratedValue(strategy = GenerationType.UUID)
    private int numero;

    @PositiveOrZero(message = "O campo SALDO NÃO pode ser negativo")
    private double saldo;


}
