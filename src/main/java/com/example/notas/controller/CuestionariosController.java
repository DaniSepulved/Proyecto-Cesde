package com.example.notas.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.notas.dto.CuestionariosDTO;
import com.example.notas.model.Cuestionarios;
import com.example.notas.service.CuestionariosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cuestionarios")
public class CuestionariosController {

    private final CuestionariosService cuestionariosService;

    public CuestionariosController(CuestionariosService cuestionariosService) {
        this.cuestionariosService = cuestionariosService;
    }

    @PostMapping
    public ResponseEntity<Cuestionarios> crear(@Valid @RequestBody CuestionariosDTO dto) {
        return ResponseEntity.ok(cuestionariosService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Cuestionarios>> listar() {
        return ResponseEntity.ok(cuestionariosService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuestionarios> buscarPorId(@PathVariable Long id) {
        Cuestionarios cuestionario = cuestionariosService.buscarPorId(id);
        return ResponseEntity.ok(cuestionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuestionarios> actualizar(@PathVariable Long id, @Valid @RequestBody CuestionariosDTO dto) {
        return ResponseEntity.ok(cuestionariosService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuestionariosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Example endpoint to list questionnaires by professor
    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<List<Cuestionarios>> listarPorProfesor(@PathVariable Long idProfesor) {
        return ResponseEntity.ok(cuestionariosService.listarPorProfesor(idProfesor));
    }
}