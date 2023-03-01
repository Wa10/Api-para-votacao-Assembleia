package com.votacao.pauta.controllers;

import com.votacao.pauta.controllers.request.SessaoRequest;
import com.votacao.pauta.controllers.request.VotacaoRequest;
import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.services.SessaoService;
import com.votacao.pauta.services.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/votacao")
@RequiredArgsConstructor
public class VotacaoController {

    private final VotacaoService service;

    @PostMapping
    public ResponseEntity<MessageResponse> votacao(@Valid @RequestBody VotacaoRequest votacaoRequest) {
        MessageResponse messageResponse = service.votar(votacaoRequest);
        return ResponseEntity.ok(messageResponse);
    }


}
