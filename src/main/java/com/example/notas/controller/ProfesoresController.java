package com.example.notas.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;
import com.example.notas.service.ProfesoresService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profesores")
public class ProfesoresController {

    private final ProfesoresService profesoresService;

    public ProfesoresController(ProfesoresService profesoresService) {
        this.profesoresService = profesoresService;
    }

    @PostMapping
    public ResponseEntity<Profesores> crear(@Valid @RequestBody ProfesoresDTO dto) {
        return ResponseEntity.ok(profesoresService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Profesores>> listar() {
        return ResponseEntity.ok(profesoresService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesores> buscarPorId(@PathVariable Long id) {
        Profesores profesor = profesoresService.buscarPorId(id);
        return ResponseEntity.ok(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesores> actualizar(@PathVariable Long id, @Valid @RequestBody ProfesoresDTO dto) {
        return ResponseEntity.ok(profesoresService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesoresService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}