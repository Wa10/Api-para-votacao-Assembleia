package com.votacao.pauta.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoRequest {
    private Long idPauta;
    private Long tempoDaSessaoEmSegundos;
}
