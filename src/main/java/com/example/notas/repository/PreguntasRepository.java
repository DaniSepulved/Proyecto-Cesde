package com.example.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.notas.model.Preguntas;
import java.util.List;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Long> {
    List<Preguntas> findByCuestionarioIdCuestionario(Long idCuestionario);
}