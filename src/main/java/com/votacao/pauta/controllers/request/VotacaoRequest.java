package com.votacao.pauta.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoRequest {
    private String cpf;
    private String voto;
    private Long idPauta;
}
