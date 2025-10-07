package com.senai.conta_bancaria.Domain.Entity;

import com.senai.conta_bancaria.Domain.exception.SaldoInsuficienteException;
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


    @Column(precision = 19, scale = 2)
    private BigDecimal limite;

    @Column(precision = 19, scale = 2)
    private BigDecimal taxa;


    @Override
    public String getTipo(){
        return "CORRENTE";
    }


    @Override
    public void sacar(BigDecimal valor) {
        validarValorMaiorQueZero(valor, "saque");

        BigDecimal custoSaque = valor.multiply(taxa);
        BigDecimal totalSaque = valor.add(custoSaque);

        if (this.getSaldo().add(this.limite).compareTo(totalSaque)<0)
        {
            throw new SaldoInsuficienteException("saque");
        }

        this.setSaldo(this.getSaldo().subtract(totalSaque));

    }
}
