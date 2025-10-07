package com.senai.conta_bancaria.Interface_UI.Exception;

import com.senai.conta_bancaria.Domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContaMesmoTipo.class)
    public ResponseEntity<String> handlerContaMesmoTipo ( ContaMesmoTipo ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<String> handlerEntidadeNaoEncontrada ( EntidadeNaoEncontradaException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RendimentoInvalidoException.class)
    public ResponseEntity<String> handlerRendimentoInvalido (RendimentoInvalidoException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handlerSaldoInsuficiente (SaldoInsuficienteException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
}
    @ExceptionHandler(TipoDeContaInvalidaException.class)
    public ResponseEntity<String> handlerTipoDeContaInvalida (TipoDeContaInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TransferirParaMesmaContaException.class)
    public ResponseEntity<String> handlerTransferirParaMesmaConta (TransferirParaMesmaContaException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValoresNegativosException.class)
    public ResponseEntity<String> handlerValoresNegativos (ValoresNegativosException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}