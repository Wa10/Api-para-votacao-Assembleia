package com.votacao.pauta.services;

import com.votacao.pauta.controllers.request.VotacaoRequest;
import com.votacao.pauta.controllers.response.MessageResponse;
import com.votacao.pauta.dtos.VotoDTO;
import com.votacao.pauta.exceptions.AssociadoVotacaoException;
import com.votacao.pauta.exceptions.SessaoException;
import com.votacao.pauta.exceptions.SessaoFinalizadaException;
import com.votacao.pauta.exceptions.VotacaoException;
import com.votacao.pauta.mappers.VotoMapper;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Sessao;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.repositories.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;
    private final PautaService pautaService;
    private final AssociadoService associadoService;
    private final VotoMapper mapper;

    public MessageResponse votar(VotoDTO votoDTO){

        associadoService.findByCPF(votoDTO.getCpf());
        Pauta pauta = pautaService.findById(votoDTO.getIdPauta());

        validacoes(votoDTO);

        List<Sessao> allSessoes = sessaoService.findAll();

        Optional<Sessao> sessaoAtual = allSessoes.stream()
                .filter(sessao -> sessao.getPauta().getId().equals(pauta.getId()))
                .findFirst();

        atualizarSessaoAtual(sessaoAtual, votoDTO);
        save(mapper.toVoto(votoDTO));

        return new MessageResponse("Voto computado do associado com cpf: " + votoDTO.getCpf() + " para a pauta de id: " + pauta.getId());
    }

    public Voto save(Voto voto){
        return votoRepository.save(voto);
    }

    public List<Voto> findAll(){
        return votoRepository.findAll();
    }

    public Optional<Voto> findByCpf(String cpf){
        return votoRepository.findByCpf(cpf);
    }

    public Optional<Voto> findByCpfAndIdPauta(String cpf, Long idPauta){
        return votoRepository.findByCpfAndIdPauta(cpf, idPauta);
    }

    public void validacoes(VotoDTO votoDTO){
        Optional<Voto> byCpfAndIdPauta = findByCpfAndIdPauta(votoDTO.getCpf(), votoDTO.getIdPauta());
        Optional<Voto> byCpf = findByCpf(votoDTO.getCpf());
        if(findByCpfAndIdPauta(votoDTO.getCpf(), votoDTO.getIdPauta()).isPresent()){
            throw new AssociadoVotacaoException("Associado com o cpf " + votoDTO.getCpf() + " já votou para essa pauta");
        }

        if(!votoDTO.getVoto().equals("Sim") && !votoDTO.getVoto().equals("Não")){
            throw new VotacaoException("Voto fora do padrão: Escreva Sim ou Não");
        }
    }

    public void atualizarSessaoAtual(Optional<Sessao> sessaoAtual, VotoDTO votoDTO){
        if(sessaoAtual.isPresent()){
            if(sessaoAtual.get().getTimestampFim() < new Date().toInstant().getEpochSecond()){
                throw new SessaoFinalizadaException("Sessão ultrapassou o periodo de votação");
            }
            if(votoDTO.getVoto().equals("Sim")){
                Long VotacaoSimAtual = sessaoAtual.get().getVotosAFavor();
                sessaoAtual.get().setVotosAFavor(++VotacaoSimAtual);
            }else if(votoDTO.getVoto().equals("Não")){
                Long votacaoNaoAtual = sessaoAtual.get().getVotosContra();
                sessaoAtual.get().setVotosContra(++votacaoNaoAtual);
            }

            sessaoService.update(sessaoAtual.get());
        }else{
            throw new SessaoException("Ainda não existe Assembléia aberta para esta pauta");
        }
    }

}
