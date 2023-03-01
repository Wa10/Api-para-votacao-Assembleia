package com.votacao.pauta.controllers;

import com.votacao.pauta.controllers.request.ResultadoVotacaoPautaResponse;
import com.votacao.pauta.dtos.PautaDTO;
import com.votacao.pauta.mappers.PautaMapper;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.services.PautaService;
import com.votacao.pauta.services.VotacaoService;
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

    private final PautaService pautaService;
    private final VotacaoService votacaoService;
    private final PautaMapper mapper;

    @PostMapping
    public ResponseEntity<PautaDTO> create(@Valid @RequestBody PautaDTO pautaDTO) {
        return new ResponseEntity<PautaDTO>(mapper.toPautaDto(pautaService.save(pautaDTO)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<PautaDTO> findById(@PathVariable Long id) {
        Pauta pauta = pautaService.findById(id);

        return ResponseEntity.ok().body(mapper.toPautaDto(pauta));
    }

    @GetMapping()
    public ResponseEntity<List<PautaDTO>> findAll(){
        List<Pauta> listaPautas = pautaService.findAll();
        List<PautaDTO> listaPautasDTO = listaPautas.stream().map(associado -> mapper.toPautaDto(associado)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaPautasDTO);
    }

    @GetMapping("/votacoes/{idPauta}")
    public ResponseEntity<ResultadoVotacaoPautaResponse> findVotacaoByIdPauta(@PathVariable Long idPauta){
        ResultadoVotacaoPautaResponse resultadoPauta = votacaoService.findVotacaoByIdPauta(idPauta);
        return ResponseEntity.ok().body(resultadoPauta);
    }

}
