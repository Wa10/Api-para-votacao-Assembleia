package com.votacao.pauta.exceptions;

public class CpfException extends RuntimeException{

    public CpfException(String message, Throwable cause){
        super(message, cause);
    }

    public CpfException(String message){
        super(message);
    }
}
