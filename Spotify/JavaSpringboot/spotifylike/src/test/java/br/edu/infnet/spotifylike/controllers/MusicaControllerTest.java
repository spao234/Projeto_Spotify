package br.edu.infnet.spotifylike.controllers;


import br.edu.infnet.spotifylike.BandaController;
import br.edu.infnet.spotifylike.MusicaController;
import br.edu.infnet.spotifylike.application.MusicaService;
import br.edu.infnet.spotifylike.domain.Musica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

@AutoConfigureMockMvc
@WebMvcTest(controllers = MusicaController.class)

public class MusicaControllerTest {

    @MockBean
    private MusicaService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void should_get_music_success() throws Exception {

        UUID id = UUID.randomUUID();

        Musica musica = new Musica();
        musica.setId(id);
        musica.setNome("Musica Dummy");
        musica.setDuracao(120);

        given(this.service.getMusica(id)).willReturn(Optional.of(musica));

        mvc.perform(get("/musica/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(musica.getId().toString())))
                .andExpect(jsonPath("$.nome", is(musica.getNome())))
                .andExpect(jsonPath("$.duracao", is(musica.getDuracao())));

    }

    @Test
    public void should_get_music_with_not_found() throws Exception {

        UUID id = UUID.randomUUID();

        given(this.service.getMusica(id)).willReturn(Optional.empty());

        mvc.perform(get("/musica/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
