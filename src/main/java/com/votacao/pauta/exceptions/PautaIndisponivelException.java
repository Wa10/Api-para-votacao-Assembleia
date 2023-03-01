package com.votacao.pauta.exceptions;

public class PautaIndisponivelException extends RuntimeException{
    public PautaIndisponivelException(String message, Throwable cause){
        super(message, cause);
    }

    public PautaIndisponivelException(String message){
        super(message);
    }
}
