package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CORRENTE")
@SuperBuilder
@NoArgsConstructor

public class ContaCorrente extends Conta {


    @Column(precision = 4)
    private BigDecimal limite;

    @Column(precision = 5)
    private BigDecimal taxa;


    @Override
    public String getTipo(){
        return "CORRENTE";
    }


    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalArgumentException("Valor inválido para saque");
        }

        BigDecimal custoSaque = valor.multiply(taxa);
        BigDecimal totalSaque = valor.add(custoSaque);

        if (getSaldo().add(limite).compareTo(totalSaque)<0)
        {
            throw new IllegalArgumentException("saldo insuficiente para saque");
        }

        setSaldo(getSaldo().subtract(totalSaque));

        super.sacar(valor);
    }
}
