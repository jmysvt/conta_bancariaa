package com.senai.conta_bancaria.Application.DTO;


import com.senai.conta_bancaria.Domain.Entity.Cliente;
import com.senai.conta_bancaria.Domain.Entity.Conta;
import com.senai.conta_bancaria.Domain.Entity.ContaCorrente;
import com.senai.conta_bancaria.Domain.Entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaResumoDTO(
        String numero,
        String tipo,
        BigDecimal saldo
){

    public Conta toEntity(Cliente cliente) {

        if ("CORRENTE".equalsIgnoreCase(tipo)){
            return ContaCorrente
                    .builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true).
                    build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaPoupanca
                    .builder()
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .build();
        }

        return null;
    }
}

