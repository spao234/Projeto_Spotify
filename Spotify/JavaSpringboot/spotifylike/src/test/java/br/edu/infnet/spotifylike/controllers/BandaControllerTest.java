package br.edu.infnet.spotifylike.controllers;

import br.edu.infnet.spotifylike.BandaController;
import br.edu.infnet.spotifylike.SpotifylikeApplication;
import br.edu.infnet.spotifylike.application.BandaService;
import br.edu.infnet.spotifylike.domain.Banda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureMockMvc
@WebMvcTest(controllers = BandaController.class)
public class BandaControllerTest {
    @MockBean
    private BandaService bandaService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @Test
    public void should_get_all_band_with_success() throws Exception{

        //Arrange
        Banda banda = new Banda();

        banda.setId(UUID.randomUUID());
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        ArrayList<Banda> bandas = new ArrayList<>();
        bandas.add(banda);

        given(bandaService.getTodos()).willReturn(bandas);

        mvc.perform(get("/banda")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is(banda.getNome())))
                .andExpect(jsonPath("$[0].id", is(banda.getId().toString())))
                .andExpect(jsonPath("$[0].descricao", is(banda.getDescricao())));
    }

    @Test
    public void should_get_band_with_id_success() throws Exception {
        UUID idBanda =  UUID.randomUUID();

        //Arrange
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        Optional<Banda> optionalBanda = Optional.of(banda);
        given(this.bandaService.getBanda(idBanda)).willReturn(optionalBanda);

        mvc.perform(get("/banda/" + idBanda.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(banda.getNome())))
                .andExpect(jsonPath("$.id", is(banda.getId().toString())))
                .andExpect(jsonPath("$.descricao", is(banda.getDescricao())));
    }

    @Test
    public void should_get_band_with_id_not_found() throws Exception {
        UUID idBanda =  UUID.randomUUID();

        //Arrange
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        given(this.bandaService.getBanda(idBanda)).willReturn(Optional.empty());

        mvc.perform(get("/banda/" + idBanda.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_post_banda_with_success() throws Exception {
        UUID idBanda =  UUID.randomUUID();

        //Arrange
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        mvc.perform(post("/banda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(banda))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is(banda.getNome())))
                .andExpect(jsonPath("$.id", is(banda.getId().toString())))
                .andExpect(jsonPath("$.descricao", is(banda.getDescricao())));
    }



}
