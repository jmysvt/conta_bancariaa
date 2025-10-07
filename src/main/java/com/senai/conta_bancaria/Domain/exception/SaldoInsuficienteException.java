package com.senai.conta_bancaria.Domain.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String operacao) {
        super ("Saldo insuficiente para realizar a operação de "+ operacao);
    }

}
