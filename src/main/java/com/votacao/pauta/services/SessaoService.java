package com.votacao.pauta.services;

import com.votacao.pauta.controllers.request.SessaoRequest;
import com.votacao.pauta.exceptions.ObjectNotFoundException;
import com.votacao.pauta.exceptions.PautaIndisponivelException;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Sessao;
import com.votacao.pauta.repositories.SessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaService pautaService;

    public Sessao save(SessaoRequest sessaoRequest) {
        Pauta pauta = pautaService.findById(sessaoRequest.getIdPauta());

        if(pauta.isIndisponivel()){
            throw new PautaIndisponivelException("Pauta sendo usada em outra sessão");
        }

        if(sessaoRequest.getTempoDaSessaoEmSegundos() == null){
            sessaoRequest.setTempoDaSessaoEmSegundos(60l);
        }

        Sessao sessao =  new Sessao(pauta, sessaoRequest.getTempoDaSessaoEmSegundos());
        Sessao sessaoCriada = sessaoRepository.save(sessao);

        pauta.setIndisponivel(true);
        pautaService.update(pauta);

        return sessaoCriada;
    }

    public void update(Sessao sessao){
        sessaoRepository.save(sessao);
    }

    public Sessao findById(Long id){
        return sessaoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado associado com id: " + id));
    }

    public List<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    public Optional<Sessao> findSessaoByIdPauta(Long id){

        List<Sessao> sessoes = findAll();
        Optional<Sessao> sessao = sessoes.stream().filter(s -> s.getPauta().getId().equals(id)).findFirst();

        return sessao;
    }
}
