package com.example.notas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notas.model.Respuestas;

public interface RespuestasRepository extends JpaRepository<Respuestas, Long> {
    Optional<Respuestas> findById(Long id_respuesta);
}
