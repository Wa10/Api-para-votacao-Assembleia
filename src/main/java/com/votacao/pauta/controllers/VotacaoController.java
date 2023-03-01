package com.votacao.pauta.controllers;

import com.votacao.pauta.controllers.request.VotacaoRequest;
import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.dtos.VotoDTO;
import com.votacao.pauta.mappers.VotoMapper;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.services.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/votacoes")
@RequiredArgsConstructor
public class VotacaoController {

    private final VotacaoService service;
    private final VotoMapper mapper;

    @PostMapping
    public ResponseEntity<MessageResponse> votacao(@Valid @RequestBody VotoDTO votoDTO) {
        MessageResponse messageResponse = service.votar(votoDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping()
    public ResponseEntity<List<VotoDTO>> findAll(){
        List<Voto> listaVotos = service.findAll();
        List<VotoDTO> listaVotosDTO = listaVotos.stream().map(voto -> mapper.toVotoDto(voto)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaVotosDTO);
    }


}
