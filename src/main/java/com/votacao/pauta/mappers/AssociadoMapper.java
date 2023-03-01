package com.votacao.pauta.mappers;

import com.votacao.pauta.dtos.AssociadoDTO;
import com.votacao.pauta.model.Associado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociadoMapper {

    private final ModelMapper mapper;

    public Associado toAssociado(AssociadoDTO associadoDTO){
        return mapper.map(associadoDTO, Associado.class);
    }

    public AssociadoDTO toAssociadoDto(Associado associado){
        return mapper.map(associado, AssociadoDTO.class);
    }
}
