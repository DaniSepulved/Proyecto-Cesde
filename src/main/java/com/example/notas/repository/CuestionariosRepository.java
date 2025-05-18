package com.example.notas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notas.model.Cuestionarios;

public interface CuestionariosRepository extends JpaRepository<Cuestionarios, Long> {
    Optional<Cuestionarios> findById(Long id_cuestionario);
}
