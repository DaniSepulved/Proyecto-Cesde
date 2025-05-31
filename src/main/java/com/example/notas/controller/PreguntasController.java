package com.example.notas.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.notas.dto.PreguntasDTO;
import com.example.notas.model.Preguntas;
import com.example.notas.service.PreguntasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntasController {

    private final PreguntasService preguntasService;

    public PreguntasController(PreguntasService preguntasService) {
        this.preguntasService = preguntasService;
    }

    @PostMapping
    public ResponseEntity<Preguntas> crear(@Valid @RequestBody PreguntasDTO dto) {
        return ResponseEntity.ok(preguntasService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Preguntas>> listar() {
        return ResponseEntity.ok(preguntasService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preguntas> buscarPorId(@PathVariable Long id) {
        Preguntas pregunta = preguntasService.buscarPorId(id);
        return ResponseEntity.ok(pregunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preguntas> actualizar(@PathVariable Long id, @Valid @RequestBody PreguntasDTO dto) {
        return ResponseEntity.ok(preguntasService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        preguntasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cuestionario/{idCuestionario}")
    public ResponseEntity<List<Preguntas>> listarPorCuestionario(@PathVariable Long idCuestionario) {
        return ResponseEntity.ok(preguntasService.listarPorCuestionario(idCuestionario));
    }
}