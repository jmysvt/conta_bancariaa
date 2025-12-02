package com.senai.conta_bancaria.Domain.exception;

public class PagamentoNaoPendenteException extends RuntimeException {
  public PagamentoNaoPendenteException() {
    super("O pagamento não está pendente");
  }
}
