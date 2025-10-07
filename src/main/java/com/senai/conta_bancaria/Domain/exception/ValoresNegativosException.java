package com.senai.conta_bancaria.Domain.exception;

public class ValoresNegativosException extends RuntimeException {
    public ValoresNegativosException(String operacao) {

      super("não é possivel realizar " + operacao+ " com valores negativos.");
    }
}
