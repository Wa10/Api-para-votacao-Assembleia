package com.votacao.pauta.services;

import com.votacao.pauta.controllers.request.ResultadoVotacaoPautaResponse;
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

import static com.votacao.pauta.dtos.EstadoPautaConstantes.*;

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

        atualizarSessaoAtual(sessaoAtual);
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

        if(findByCpfAndIdPauta(votoDTO.getCpf(), votoDTO.getIdPauta()).isPresent()){
            throw new AssociadoVotacaoException("Associado com o cpf " + votoDTO.getCpf() + " já votou para essa pauta");
        }

        if(!votoDTO.getVoto().equals("Sim") && !votoDTO.getVoto().equals("Não")){
            throw new VotacaoException("Voto fora do padrão: Escreva Sim ou Não");
        }
    }

    public void atualizarSessaoAtual(Optional<Sessao> sessaoAtual){
        if(sessaoAtual.isPresent()){
            if(sessaoAtual.get().getTimestampFim() < new Date().toInstant().getEpochSecond()){
                throw new SessaoFinalizadaException("Sessão ultrapassou o periodo de votação");
            }

            sessaoService.update(sessaoAtual.get());
        }else{
            throw new SessaoException("Ainda não existe Assembléia aberta para esta pauta");
        }
    }

    public List<Voto> findAllByIdPauta(Long id){
        return votoRepository.findAllByIdPauta(id);
    }

    public ResultadoVotacaoPautaResponse findVotacaoByIdPauta(Long id) {
        ResultadoVotacaoPautaResponse resultadoVotacao = new ResultadoVotacaoPautaResponse();

        Pauta pauta = pautaService.findById(id);
        resultadoVotacao.setIdPauta(pauta.getId());
        resultadoVotacao.setNomePauta(pauta.getNomePauta());

        Optional<Sessao> sessao = sessaoService.findSessaoByIdPauta(pauta.getId());

        if(sessao.isPresent()){
            if(sessao.get().getTimestampFim() >= new Date().toInstant().getEpochSecond()){
                resultadoVotacao.setEstado(EM_USO);
            } else {
                resultadoVotacao.setEstado(FINALIZADA);
            }
        }else {
            resultadoVotacao.setEstado(NAO_INICIADA);
        }

        List<Voto> votos = findAllByIdPauta(pauta.getId());

        long votosFavoraveis = votos.stream().filter(voto -> voto.getVoto().equals("Sim")).count();
        long votosContrarios = votos.stream().filter(voto -> voto.getVoto().equals("Não")).count();

        resultadoVotacao.setVotosFavoraveis(votosFavoraveis);
        resultadoVotacao.setVotosContrario(votosContrarios);

        return resultadoVotacao;
    }

}
