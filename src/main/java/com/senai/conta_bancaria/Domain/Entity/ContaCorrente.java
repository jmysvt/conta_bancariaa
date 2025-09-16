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

}
