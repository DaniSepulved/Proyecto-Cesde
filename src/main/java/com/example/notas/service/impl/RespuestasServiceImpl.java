package com.example.notas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.notas.dto.RespuestasDTO;
import com.example.notas.model.Respuestas;
import com.example.notas.model.Preguntas;
import com.example.notas.model.Estudiantes;
import com.example.notas.repository.RespuestasRepository;
import com.example.notas.repository.PreguntasRepository;
import com.example.notas.repository.EstudiantesRepository; 
import com.example.notas.service.RespuestasService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RespuestasServiceImpl implements RespuestasService {

    private final RespuestasRepository respuestasRepository;
    private final PreguntasRepository preguntasRepository;
    private final EstudiantesRepository estudiantesRepository;

    public RespuestasServiceImpl(RespuestasRepository respuestasRepository,
                                 PreguntasRepository preguntasRepository,
                                 EstudiantesRepository estudiantesRepository) {
        this.respuestasRepository = respuestasRepository;
        this.preguntasRepository = preguntasRepository;
        this.estudiantesRepository = estudiantesRepository;
    }

    @Override
    public Respuestas crear(RespuestasDTO dto) {
        Preguntas pregunta = preguntasRepository.findById(dto.getId_pregunta())
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada con id: " + dto.getId_pregunta()));

        Estudiantes estudiante = estudiantesRepository.findById(dto.getId_estudiante())
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + dto.getId_estudiante()));

        Respuestas respuesta = Respuestas.builder()
                .preguntas(pregunta)
                .estudiantes(estudiante)
                .build();
        return respuestasRepository.save(respuesta);
    }

    @Override
    public List<Respuestas> listar() {
        return respuestasRepository.findAll();
    }

    @Override
    public Respuestas buscarPorId(Long idRespuesta) {
        return respuestasRepository.findById(idRespuesta)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada con id: " + idRespuesta));
    }

    @Override
    public Respuestas actualizar(Long idRespuesta, RespuestasDTO dto) {
        Respuestas respuesta = buscarPorId(idRespuesta);

        Preguntas pregunta = preguntasRepository.findById(dto.getId_pregunta())
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada con id: " + dto.getId_pregunta()));

        Estudiantes estudiante = estudiantesRepository.findById(dto.getId_estudiante())
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + dto.getId_estudiante()));

        respuesta.setPreguntas(pregunta);
        respuesta.setEstudiantes(estudiante);
        
        return respuestasRepository.save(respuesta);
    }

    @Override
    public void eliminar(Long idRespuesta) {
        Respuestas respuesta = buscarPorId(idRespuesta);
        respuestasRepository.delete(respuesta);
    }

    @Override
    public List<Respuestas> listarPorPregunta(Long idPregunta) {
        if (!preguntasRepository.existsById(idPregunta)) {
            throw new EntityNotFoundException("Pregunta no encontrada con id: " + idPregunta);
        }
        return respuestasRepository.findByPreguntasIdPregunta(idPregunta);
    }

    @Override
    public List<Respuestas> listarPorEstudiante(Long idEstudiante) {
        if (!estudiantesRepository.existsById(idEstudiante)) {
            throw new EntityNotFoundException("Estudiante no encontrado con id: " + idEstudiante);
        }
        return respuestasRepository.findByEstudiantesIdEstudiante(idEstudiante);
    }
}
