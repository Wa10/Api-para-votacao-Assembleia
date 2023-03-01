package com.votacao.pauta.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(PropertyValueException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Necessita de um nome de pauta", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(ObjectNotFoundException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"Objecto não encontrado", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CpfException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(CpfException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Problemas com o CPF", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(VotacaoException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(VotacaoException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Voto fora do padrão", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SessaoException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(SessaoException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Sessão não iniciada para essa Pauta", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SessaoFinalizadaException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(SessaoFinalizadaException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Sessão Finalizada", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PautaIndisponivelException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(PautaIndisponivelException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Pauta já usada em outra sessão", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
