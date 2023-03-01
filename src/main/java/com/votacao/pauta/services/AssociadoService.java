package com.votacao.pauta.services;

import com.votacao.pauta.dtos.AssociadoDTO;
import com.votacao.pauta.exceptions.CpfException;
import com.votacao.pauta.exceptions.ObjectNotFoundException;
import com.votacao.pauta.mappers.AssociadoMapper;
import com.votacao.pauta.model.Associado;
import com.votacao.pauta.repositories.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository repository;
    private final AssociadoMapper mapper;

    public Associado findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado associado com id: " + id));
    }
    public Associado findByCPF(String cpf){
        Optional<Associado> associado = repository.findByCpf(cpf);
        if(associado.isEmpty()){
            throw new CpfException("Associado não encontrado");
        }

        return associado.get();
    }

    public Associado save(AssociadoDTO associadoDTO){
        validarPorCPF(associadoDTO);

        return repository.save(mapper.toAssociado(associadoDTO));
    }

    public List<Associado> findAll() {
        return repository.findAll();
    }

    public Associado update(Long id, AssociadoDTO associadoDTO) {
        Associado funcionarioExistente = findById(id);
        associadoDTO.setId(id);
        validarPorCPF(associadoDTO);
        funcionarioExistente = mapper.toAssociado(associadoDTO);

        return repository.save(funcionarioExistente);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    private void validarPorCPF(AssociadoDTO associadoDTO) {
        Optional<Associado> associado = repository.findByCpf(associadoDTO.getCpf());
        if(associado.isPresent()){
            throw new CpfException("CPF duplicado");
        }
    }


}
