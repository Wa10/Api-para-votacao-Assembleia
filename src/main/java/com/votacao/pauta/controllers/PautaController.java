package com.votacao.pauta.controllers;

import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.dtos.AssociadoDTO;
import com.votacao.pauta.dtos.PautaDTO;
import com.votacao.pauta.mappers.PautaMapper;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.services.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService service;
    private final PautaMapper mapper;

    @PostMapping
    public ResponseEntity<PautaDTO> create(@Valid @RequestBody PautaDTO pautaDTO) {
        return new ResponseEntity<PautaDTO>(mapper.toPautaDto(service.save(pautaDTO)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<PautaDTO> findById(@PathVariable Long id) {
        Pauta pauta = service.findById(id);

        return ResponseEntity.ok().body(mapper.toPautaDto(pauta));
    }

    @GetMapping()
    public ResponseEntity<List<PautaDTO>> findAll(){
        List<Pauta> listaPautas = service.findAll();
        List<PautaDTO> listaPautasDTO = listaPautas.stream().map(associado -> mapper.toPautaDto(associado)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaPautasDTO);
    }

}
