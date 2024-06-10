package br.edu.infnet.spotifylike.application;

import br.edu.infnet.spotifylike.domain.Musica;
import br.edu.infnet.spotifylike.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository repository;

    public Optional<Musica> getMusica(UUID id) {
        return this.repository.findById(id);
    }

}
