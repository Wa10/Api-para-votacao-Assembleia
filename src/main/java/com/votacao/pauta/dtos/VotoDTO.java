package com.votacao.pauta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

    private Long id;

    private String cpf;
    private String voto;
    private Long idPauta;
}
