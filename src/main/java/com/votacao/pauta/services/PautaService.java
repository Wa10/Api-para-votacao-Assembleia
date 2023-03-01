package com.votacao.pauta.services;

import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.dtos.PautaDTO;
import com.votacao.pauta.exceptions.ObjectNotFoundException;
import com.votacao.pauta.mappers.PautaMapper;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.repositories.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper mapper;

    public Pauta findById(Long id){
        return pautaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi encontrada pauta com id: " + id));
    }

    public Pauta save(PautaDTO pautaDTO){
        return pautaRepository.save(mapper.toPauta(pautaDTO));
    }

    public void update(Pauta pauta){
        pautaRepository.save(pauta);
    }

    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }


}
