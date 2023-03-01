package com.votacao.pauta.exceptions;

public class SessaoFinalizadaException extends RuntimeException{

    public SessaoFinalizadaException(String message, Throwable cause){
        super(message, cause);
    }

    public SessaoFinalizadaException(String message){
        super(message);
    }
}
