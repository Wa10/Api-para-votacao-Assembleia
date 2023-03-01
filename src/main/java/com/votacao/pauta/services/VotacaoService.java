package com.votacao.pauta.services;

import com.votacao.pauta.controllers.request.VotacaoRequest;
import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.exceptions.SessaoException;
import com.votacao.pauta.exceptions.SessaoFinalizadaException;
import com.votacao.pauta.exceptions.VotacaoException;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Sessao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final SessaoService sessaoService;
    private final PautaService pautaService;
    private final AssociadoService associadoService;

    public MessageResponse votar(VotacaoRequest votacaoRequest){

        if(!votacaoRequest.getVoto().equals("Sim") && !votacaoRequest.getVoto().equals("Não")){
            throw new VotacaoException("Voto fora do padrão: Escreva Sim ou Não");
        }

        associadoService.findByCPF(votacaoRequest.getCpf());
        Pauta pauta = pautaService.findById(votacaoRequest.getIdPauta());
        List<Sessao> allSessoes = sessaoService.findAll();

        Optional<Sessao> sessaoAtual = allSessoes.stream()
                .filter(sessao -> sessao.getPauta().getId().equals(pauta.getId()))
                .findFirst();

        if(sessaoAtual.isPresent()){
            if(sessaoAtual.get().getTimestampFim() < new Date().toInstant().getEpochSecond()){
                throw new SessaoFinalizadaException("Sessão ultrapassou o periodo de votação");
            }
            if(votacaoRequest.getVoto().equals("Sim")){
                Long VotacaoSimAtual = sessaoAtual.get().getVotosAFavor();
                sessaoAtual.get().setVotosAFavor(++VotacaoSimAtual);
            }else if(votacaoRequest.getVoto().equals("Não")){
                Long votacaoNaoAtual = sessaoAtual.get().getVotosContra();
                sessaoAtual.get().setVotosContra(++votacaoNaoAtual);
            }
            sessaoService.update(sessaoAtual.get());
        }else{
            throw new SessaoException("Ainda não existe Assembléia aberta para esta pauta");
        }

        return new MessageResponse("Voto computado do associado com cpf: " + votacaoRequest.getCpf() + " para a pauta de id: " + pauta.getId());
    }

}
