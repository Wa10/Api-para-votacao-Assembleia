package com.votacao.pauta.repositories;

import com.votacao.pauta.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
