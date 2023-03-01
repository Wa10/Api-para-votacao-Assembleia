package com.votacao.pauta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JsonIgnore
    @OneToOne
    private Pauta pauta;

    private Long timestampInicio;

    private Long timestampFim;

    public Sessao(Pauta pauta, Long duracaoSessao){
        this.pauta = pauta;
        this.timestampInicio = new Date().toInstant().getEpochSecond();
        this.timestampFim = this.timestampInicio + duracaoSessao;
    }
}
