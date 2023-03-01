package com.votacao.pauta.exceptions;

public class VotacaoException extends RuntimeException{

    public VotacaoException(String message, Throwable cause){
        super(message, cause);
    }

    public VotacaoException(String message){
        super(message);
    }
}
