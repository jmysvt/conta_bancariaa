package com.senai.conta_bancaria.Domain.exception;

public class RendimentoInvalidoException extends RuntimeException {
  public RendimentoInvalidoException() {
    super(
            "rendimento dever apenas em conta poupança"
    );
  }
}
