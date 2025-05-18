package com.example.notas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notas.model.Profesores;

public interface ProfesoresRepository extends JpaRepository<Profesores, Long> {
    Optional<Profesores> findById(Long id_profesor);

}
