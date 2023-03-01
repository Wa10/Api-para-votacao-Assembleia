package com.votacao.pauta.dtos;

import com.votacao.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDTO {

    private Long Id;
    private Pauta pauta;
    private Long timestampInicio;
    private Long timestampFim;
}
