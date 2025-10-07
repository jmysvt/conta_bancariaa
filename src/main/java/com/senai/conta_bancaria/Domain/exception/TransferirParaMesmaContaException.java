package com.senai.conta_bancaria.Domain.exception;

public class TransferirParaMesmaContaException extends RuntimeException {
    public TransferirParaMesmaContaException() {

        super("Não é possivel transferir para a mesma conta");
    }
}
