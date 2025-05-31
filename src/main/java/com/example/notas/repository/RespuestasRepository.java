package com.example.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.notas.model.Respuestas;
import java.util.List;

@Repository
public interface RespuestasRepository extends JpaRepository<Respuestas, Long> {
    List<Respuestas> findByPreguntasIdPregunta(Long idPregunta);
    List<Respuestas> findByEstudiantesIdEstudiante(Long idEstudiante);
    List<Respuestas> findByPreguntasIdPreguntaAndEstudiantesIdEstudiante(Long idPregunta, Long idEstudiante);
}
