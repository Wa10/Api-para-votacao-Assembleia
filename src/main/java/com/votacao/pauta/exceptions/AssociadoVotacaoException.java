package com.votacao.pauta.exceptions;

public class AssociadoVotacaoException extends RuntimeException{
    public AssociadoVotacaoException(String message, Throwable cause){
        super(message, cause);
    }

    public AssociadoVotacaoException(String message){
        super(message);
    }
}
