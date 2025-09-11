package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.ContaPoupanca;

public record ContaPoupancaDTO(
        int numero,
        double saldo,
        double rendimento
) {
    public static ContaPoupancaDTO fromEntity(ContaPoupanca conta) {
        if (conta == null) return null;
        return new ContaPoupancaDTO(
                conta.getNumero(),
                conta.getSaldo(),
                conta.getRendimento()
        );
    }

    public ContaPoupanca toEntity() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.setNumero(this.numero);
        conta.setSaldo(this.saldo);
        conta.setRendimento(this.rendimento);
        return conta;
    }
}
