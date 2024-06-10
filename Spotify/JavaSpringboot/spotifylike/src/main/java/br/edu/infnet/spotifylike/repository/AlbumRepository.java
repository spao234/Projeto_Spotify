package br.edu.infnet.spotifylike.repository;

import br.edu.infnet.spotifylike.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}
