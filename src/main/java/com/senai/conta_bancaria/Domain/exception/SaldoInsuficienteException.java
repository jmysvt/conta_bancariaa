package com.senai.conta_bancaria.Domain.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String operacao) {
        super ("Saldo induficeiento para realixar a operação de"+ operacao);
    }

}
