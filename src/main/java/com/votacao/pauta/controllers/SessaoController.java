package com.votacao.pauta.controllers;

import com.votacao.pauta.controllers.request.SessaoRequest;
import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.dtos.PautaDTO;
import com.votacao.pauta.dtos.SessaoDTO;
import com.votacao.pauta.mappers.SessaoMapper;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Sessao;
import com.votacao.pauta.services.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService service;
    private final SessaoMapper mapper;

    @PostMapping
    public ResponseEntity<SessaoDTO> create(@Valid @RequestBody SessaoRequest sessaoRequest) {
        return new ResponseEntity<SessaoDTO>(mapper.toSessaoDto(service.save(sessaoRequest)), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<SessaoDTO>> findAll(){
        List<Sessao> listaPautas = service.findAll();
        List<SessaoDTO> listaPautasDTO = listaPautas.stream().map(associado -> mapper.toSessaoDto(associado)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaPautasDTO);
    }

}
