package com.senai.conta_bancaria.Domain.exception;

public class PagamentoNaoAssociadoTaxaException extends RuntimeException {
    public PagamentoNaoAssociadoTaxaException(String message) {
        super("O pagamento deve ter pelo menos uma taxa associada");
    }
}
