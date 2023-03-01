package com.votacao.pauta.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoPautaResponse {

    private Long idPauta;
    private String nomePauta;
    private String estado;
    private Long votosFavoraveis;
    private Long votosContrario;
}
