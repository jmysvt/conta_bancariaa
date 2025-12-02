package com.senai.conta_bancaria.Domain.exception;

public class BoletoVencidoException extends RuntimeException {
    public BoletoVencidoException(String message) {
        super("o boleto está vencido e não pode ser pago");
    }
}
