package com.votacao.pauta.repositories;

import com.votacao.pauta.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByCpf(String cpf);

    Optional<Voto> findByCpfAndIdPauta(String cpf, Long idPauta);
}
