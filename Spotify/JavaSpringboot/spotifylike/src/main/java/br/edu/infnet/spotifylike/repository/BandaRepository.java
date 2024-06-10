package br.edu.infnet.spotifylike.repository;

import br.edu.infnet.spotifylike.domain.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BandaRepository extends JpaRepository<Banda, UUID> {
}
