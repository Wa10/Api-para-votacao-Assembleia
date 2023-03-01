package com.votacao.pauta.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionError {

    private Long timestamp;
    private Integer status;
    private String message;
    private String error;
    private String path;


}
