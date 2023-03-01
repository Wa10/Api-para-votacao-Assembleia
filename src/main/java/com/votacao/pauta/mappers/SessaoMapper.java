package com.votacao.pauta.mappers;

import com.votacao.pauta.dtos.PautaDTO;
import com.votacao.pauta.dtos.SessaoDTO;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Sessao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessaoMapper {

    private final ModelMapper mapper;

    public Sessao toSessao(SessaoDTO sessaoDTO){
        return mapper.map(sessaoDTO, Sessao.class);
    }

    public SessaoDTO toSessaoDto(Sessao sessao){
        return mapper.map(sessao, SessaoDTO.class);
    }
}
