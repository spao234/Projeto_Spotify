package br.edu.infnet.spotifylike.application;

import br.edu.infnet.spotifylike.domain.Banda;
import br.edu.infnet.spotifylike.repository.BandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BandaService {

    @Autowired
    private BandaRepository repository;

    public void create(Banda banda) {
        this.repository.save(banda);
    }

    public Optional<Banda> getBanda(UUID id) {
        return this.repository.findById(id);
    }

    public List<Banda> getTodos() {
        return this.repository.findAll();
    }

}
