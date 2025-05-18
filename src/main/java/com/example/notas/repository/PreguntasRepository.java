package com.example.notas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notas.model.Preguntas;

public interface PreguntasRepository extends JpaRepository<Preguntas, Long> {
    Optional<Preguntas> findById(Long id_pregunta);
}