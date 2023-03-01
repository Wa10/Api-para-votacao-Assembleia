package com.votacao.pauta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

    private Long id;

    @NotBlank
    private String nomePauta;

    private boolean indisponivel;
}
