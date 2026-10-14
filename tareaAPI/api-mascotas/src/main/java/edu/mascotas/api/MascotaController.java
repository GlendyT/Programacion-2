package edu.mascotas.api;

import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaRepository mascotaRepository;

    public MascotaController(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listar() {
        JSONArray respuesta = mascotaRepository.listarComoJson();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(respuesta.toString());
    }
}
