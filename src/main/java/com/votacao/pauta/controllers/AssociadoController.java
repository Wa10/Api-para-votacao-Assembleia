package com.votacao.pauta.controllers;

import com.votacao.pauta.dtos.AssociadoDTO;
import com.votacao.pauta.mappers.AssociadoMapper;
import com.votacao.pauta.model.Associado;
import com.votacao.pauta.services.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/associados")
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService service;
    private final AssociadoMapper mapper;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id) {
        Associado associado = service.findById(id);

        return ResponseEntity.ok().body(mapper.toAssociadoDto(associado));
    }

    @GetMapping()
    public ResponseEntity<List<AssociadoDTO>> findAll(){
        List<Associado> listaAssociados = service.findAll();
        List<AssociadoDTO> ListaAssociadosDTO = listaAssociados.stream().map(associado -> mapper.toAssociadoDto(associado)).collect(Collectors.toList());

        return ResponseEntity.ok().body(ListaAssociadosDTO);
    }

    @PostMapping
    public ResponseEntity<AssociadoDTO> create(@Valid @RequestBody AssociadoDTO novoAssociadoDTO) {
        Associado associado = service.save(novoAssociadoDTO);

        return new ResponseEntity<AssociadoDTO>(mapper.toAssociadoDto(associado),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AssociadoDTO> update(@PathVariable Long id, @Valid @RequestBody AssociadoDTO associadoDTO){
        Associado associado = service.update(id, associadoDTO);

        return ResponseEntity.ok().body(mapper.toAssociadoDto(associado));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AssociadoDTO> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
