package com.votacao.pauta.mappers;

import com.votacao.pauta.dtos.PautaDTO;
import com.votacao.pauta.model.Pauta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PautaMapper {

    private final ModelMapper mapper;

    public Pauta toPauta(PautaDTO pautaDTO){
        return mapper.map(pautaDTO, Pauta.class);
    }

    public PautaDTO toPautaDto(Pauta pauta){
        return mapper.map(pauta, PautaDTO.class);
    }
}
