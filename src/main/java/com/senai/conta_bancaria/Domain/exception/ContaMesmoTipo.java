package com.senai.conta_bancaria.Domain.exception;

public class ContaMesmoTipo extends RuntimeException {
    public ContaMesmoTipo() {

      super("Não é possivel criar para uma conta do mesmo tipo");
    }
}
