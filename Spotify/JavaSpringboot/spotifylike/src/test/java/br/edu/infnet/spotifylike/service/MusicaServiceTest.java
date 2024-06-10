package br.edu.infnet.spotifylike.service;

import br.edu.infnet.spotifylike.application.BandaService;
import br.edu.infnet.spotifylike.application.MusicaService;
import br.edu.infnet.spotifylike.domain.Banda;
import br.edu.infnet.spotifylike.domain.Musica;
import br.edu.infnet.spotifylike.repository.BandaRepository;
import br.edu.infnet.spotifylike.repository.MusicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class MusicaServiceTest {

    @MockBean
    private MusicaRepository repository;

    @Autowired
    private MusicaService service;

    @Test
    public void should_get_music_with_id_success() {
        UUID id = UUID.randomUUID();

        Musica musica = new Musica();
        musica.setId(id);
        musica.setNome("Musica Dummy");
        musica.setDuracao(120);

        Optional<Musica> optionalMusica = Optional.of(musica);
        given(this.repository.findById(id)).willReturn(optionalMusica);

        Optional<Musica> expected = this.service.getMusica(id);
        Assertions.assertTrue(expected.isPresent());
        Assertions.assertSame(expected, optionalMusica);
    }

}
