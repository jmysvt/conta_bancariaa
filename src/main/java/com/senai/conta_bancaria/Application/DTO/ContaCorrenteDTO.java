package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.ContaCorrente;

import java.math.BigDecimal;

public record ContaCorrenteDTO(
        String numero,
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal taxa
) {
    public static ContaCorrenteDTO fromEntity(ContaCorrente conta) {
        if (conta == null) return null;
        return new ContaCorrenteDTO(
                conta.getNumero(),
                conta.getSaldo(),
                conta.getLimite(),
                conta.getTaxa()
        );
    }

    public ContaCorrente toEntity() {
        ContaCorrente conta = new ContaCorrente();
        conta.setNumero(this.numero);
        conta.setSaldo(this.saldo);
        conta.setLimite(this.limite);
        conta.setTaxa(this.taxa);
        return conta;
    }
}
