package com.senai.conta_bancaria.Domain.exception;

public class PagamentoInvalidoException extends RuntimeException {
    public PagamentoInvalidoException() {
        super("Pagamento inválido. Erro ao processar.");
    }
}
