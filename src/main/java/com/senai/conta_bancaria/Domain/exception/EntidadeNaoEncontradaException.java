package com.senai.conta_bancaria.Domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(entidade + "não existente ou inválido(a)!");
    }
}
