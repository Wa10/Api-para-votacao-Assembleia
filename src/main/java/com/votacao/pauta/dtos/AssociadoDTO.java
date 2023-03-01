package com.votacao.pauta.dtos;

import com.votacao.pauta.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO implements Serializable {
    private Long id;
    private String nome;
    private String cpf;
}
