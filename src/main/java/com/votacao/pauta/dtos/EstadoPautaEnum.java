package com.votacao.pauta.dtos;

import lombok.Getter;

@Getter
enum EstadoPautaEnum {
    NAO_INICIADA("Sessão não iniciada"),
    EM_USO("Votacao aberta"),
    FINALIZADA("Votacao fechada");

    private String periodo;

    EstadoPautaEnum(String periodo){
        this.periodo = periodo;
    }
}
