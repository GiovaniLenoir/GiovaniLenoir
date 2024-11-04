package com.loja.exception;

public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String message) {
        super(message);
    }
    public ValidacaoException(String message, Throwable causa){
        super(message, causa);
    }
}
