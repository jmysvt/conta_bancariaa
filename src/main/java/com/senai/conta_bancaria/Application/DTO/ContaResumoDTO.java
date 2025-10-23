package com.senai.conta_bancaria.Application.DTO;


import com.senai.conta_bancaria.Domain.Entity.Cliente;
import com.senai.conta_bancaria.Domain.Entity.Conta;
import com.senai.conta_bancaria.Domain.Entity.ContaCorrente;
import com.senai.conta_bancaria.Domain.Entity.ContaPoupanca;
import com.senai.conta_bancaria.Domain.exception.TipoDeContaInvalidaException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ContaResumoDTO(
        @NotBlank(message = "O número da conta não pode estar em branco")
        String numero,

        @NotBlank(message = "O tipo da conta é obrigatório (CORRENTE ou POUPANCA)")
        String tipo,

        @NotNull(message = "O saldo não pode ser nulo")
        @Positive(message = "O valor do saldo deve ser positivo")

        BigDecimal saldo
){
    public Conta toEntity (Cliente cliente){
        if ("CORRENTE".equalsIgnoreCase(tipo)){
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .taxa(new BigDecimal("0.05"))
                    .limite(new BigDecimal("500.0"))
                    .ativa(true)
                    .build();
        }else if ("POUPANCA".equalsIgnoreCase(tipo)){
            return ContaPoupanca.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .rendimento(new BigDecimal("0.01"))
                    .ativa(true)
                    .build();
        }
        throw new TipoDeContaInvalidaException(" ");
    }

    public static ContaResumoDTO fromEntity(Conta conta) {
        return new ContaResumoDTO(
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo()
        );
    }
}

