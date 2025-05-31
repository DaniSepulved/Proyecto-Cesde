package com.example.notas.controller;

import java.util.List; // Import List

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; // Import DeleteMapping
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Import PathVariable
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // Import PutMapping
// Import RequestBody from org.springframework.web.bind.annotation
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.service.EstudiantesService;

// Remove the swagger RequestBody import if you are using spring's RequestBody
// import io.swagger.v3.oas.annotations.parameters.RequestBody; // Redundant if using Spring's
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesController {
    private final EstudiantesService estudiantesService;

    public EstudiantesController(EstudiantesService estudiantesService) {
        this.estudiantesService = estudiantesService;
    }

    @PostMapping
    public ResponseEntity<Estudiantes> crear(@Valid @RequestBody EstudiantesDTO dto) {
        return ResponseEntity.ok(estudiantesService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Estudiantes>> listar() { // Changed Iterable to List
        return ResponseEntity.ok(estudiantesService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiantes> buscarPorId(@PathVariable Long id) {
        // Assuming your EstudiantesService will have a buscarPorId method
        Estudiantes estudiante = estudiantesService.buscarPorId(id);
        if (estudiante != null) {
            return ResponseEntity.ok(estudiante);
        } else {
            return ResponseEntity.notFound().build(); // Or handle as per your service logic
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiantes> actualizar(@PathVariable Long id, @Valid @RequestBody EstudiantesDTO dto) {
        // Assuming your EstudiantesService will have an actualizar method
        return ResponseEntity.ok(estudiantesService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Assuming your EstudiantesService will have an eliminar method
        estudiantesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
