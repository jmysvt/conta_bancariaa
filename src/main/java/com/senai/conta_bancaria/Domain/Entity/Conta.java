package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //entidades com tipos diferentes
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20) //tipo de dado que vai diferenciar as entidades
@Table(name = "contaDTO",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
        @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
}) //regras de limitação

@Data
@SuperBuilder
@NoArgsConstructor

public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    @NotNull(message = "O campo NUMERO da contaDTO NÃO pode estar vazio")
    private String numero;

    @Column(nullable = false, precision = 4)
    @PositiveOrZero(message = "O campo SALDO NÃO pode ser negativo")
    private BigDecimal saldo;

    @Column(nullable = false)
    private boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;

    public abstract String getTipo();

    public void sacar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0  ){
            validarValorMaiorQueZero(valor);
        }

        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }

        saldo = saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        validarValorMaiorQueZero(valor);
        saldo = saldo.add(valor);
    }

    private static void validarValorMaiorQueZero(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("O valor para deposito deve ser positivo");
        }
    }
}
