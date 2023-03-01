package com.votacao.pauta.mappers;

import com.votacao.pauta.dtos.VotoDTO;
import com.votacao.pauta.model.Voto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotoMapper {

    private final ModelMapper mapper;

    public Voto toVoto(VotoDTO votoDTO){
        return mapper.map(votoDTO, Voto.class);
    }

    public VotoDTO toVotoDto(Voto voto){
        return mapper.map(voto, VotoDTO.class);
    }
}
