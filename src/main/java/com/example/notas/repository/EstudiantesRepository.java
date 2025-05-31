package com.example.notas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notas.model.Estudiantes;

public interface EstudiantesRepository extends JpaRepository<Estudiantes, Long> {
    Optional<Estudiantes> findByEmail(String email);
}
