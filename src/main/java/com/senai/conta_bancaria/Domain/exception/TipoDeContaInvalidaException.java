package com.senai.conta_bancaria.Domain.exception;

public class TipoDeContaInvalidaException extends RuntimeException {
    public TipoDeContaInvalidaException(String tipo){
        super("Tipo de conta" +tipo+ " inválida. Os tipo são: 'corrente' ou 'poupança'");
    }
}
