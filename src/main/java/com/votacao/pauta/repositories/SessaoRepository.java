package com.votacao.pauta.repositories;

import com.votacao.pauta.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    
}
