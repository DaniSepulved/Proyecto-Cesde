package com.example.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.notas.model.Cuestionarios;
import java.util.List;

@Repository
public interface CuestionariosRepository extends JpaRepository<Cuestionarios, Long> {
    List<Cuestionarios> findByProfesorIdProfesor(Long idProfesor);
}