package br.edu.infnet.spotifylike.service;

import br.edu.infnet.spotifylike.application.BandaService;
import br.edu.infnet.spotifylike.domain.Banda;
import br.edu.infnet.spotifylike.repository.BandaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest

public class BandaServiceTest {

    @MockBean
    private BandaRepository repository;

    @Autowired
    private BandaService service;

    @Test
    public void should_get_all_band_success() {
        Banda banda = new Banda();

        banda.setId(UUID.randomUUID());
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        ArrayList<Banda> bandas = new ArrayList<>();
        bandas.add(banda);

        given(this.repository.findAll()).willReturn(bandas);

        List<Banda> expected = this.service.getTodos();
        Assertions.assertArrayEquals(bandas.toArray(), expected.toArray());
    }

    @Test
    public void should_get_by_id_band_success() {
        UUID idBanda =  UUID.randomUUID();

        //Arrange
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        Optional<Banda> optionalBanda = Optional.of(banda);
        given(this.repository.findById(idBanda)).willReturn(optionalBanda);

        Optional<Banda> expected = this.service.getBanda(idBanda);
        Assertions.assertTrue(expected.isPresent());
        Assertions.assertSame(expected, optionalBanda);
    }

    @Test
    public void should_create_band_success() {
        UUID idBanda =  UUID.randomUUID();

        //Arrange
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        this.service.create(banda);

        verify(this.repository, times(1)).save(banda);

    }

}
