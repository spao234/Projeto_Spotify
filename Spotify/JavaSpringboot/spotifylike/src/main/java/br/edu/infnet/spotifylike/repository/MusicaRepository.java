package br.edu.infnet.spotifylike.repository;

import br.edu.infnet.spotifylike.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID> {

}
