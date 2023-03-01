package com.votacao.pauta.exceptions;

public class SessaoException extends RuntimeException{

    public SessaoException(String message, Throwable cause){
        super(message, cause);
    }

    public SessaoException(String message){
        super(message);
    }
}
