package br.edu.infnet.spotifylike;

import br.edu.infnet.spotifylike.application.BandaService;
import br.edu.infnet.spotifylike.domain.Banda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/banda")
public class BandaController {
    @Autowired
    private BandaService bandaService;
    @GetMapping
    public ResponseEntity<List<Banda>> getAll() {
        List<Banda> banda = this.bandaService.getTodos();
        return new ResponseEntity<>(banda, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Banda> get(@PathVariable("id") UUID id) {
            return this.bandaService.getBanda(id).map(x -> {
                return new ResponseEntity<Banda>(x, HttpStatus.OK);
            }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Banda> create(@RequestBody Banda banda) {
        this.bandaService.create(banda);
        return new ResponseEntity<>(banda, HttpStatus.CREATED);
    }

}
