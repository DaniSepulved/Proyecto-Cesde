package com.example.notas.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.notas.dto.RespuestasDTO;
import com.example.notas.model.Respuestas;
import com.example.notas.service.RespuestasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestasController {

    private final RespuestasService respuestasService;

    public RespuestasController(RespuestasService respuestasService) {
        this.respuestasService = respuestasService;
    }

    @PostMapping
    public ResponseEntity<Respuestas> crear(@Valid @RequestBody RespuestasDTO dto) {
        return ResponseEntity.ok(respuestasService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Respuestas>> listar() {
        return ResponseEntity.ok(respuestasService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuestas> buscarPorId(@PathVariable Long id) {
        Respuestas respuesta = respuestasService.buscarPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuestas> actualizar(@PathVariable Long id, @Valid @RequestBody RespuestasDTO dto) {
        return ResponseEntity.ok(respuestasService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        respuestasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pregunta/{idPregunta}")
    public ResponseEntity<List<Respuestas>> listarPorPregunta(@PathVariable Long idPregunta) {
        return ResponseEntity.ok(respuestasService.listarPorPregunta(idPregunta));
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<Respuestas>> listarPorEstudiante(@PathVariable Long idEstudiante) {
        return ResponseEntity.ok(respuestasService.listarPorEstudiante(idEstudiante));
    }
}
