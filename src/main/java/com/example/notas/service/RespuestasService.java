package com.example.notas.service;

import java.util.List;
import com.example.notas.dto.RespuestasDTO;
import com.example.notas.model.Respuestas;

public interface RespuestasService {
    Respuestas crear(RespuestasDTO dto);
    List<Respuestas> listar();
    Respuestas buscarPorId(Long idRespuesta);
    Respuestas actualizar(Long idRespuesta, RespuestasDTO dto);
    void eliminar(Long idRespuesta);
    List<Respuestas> listarPorPregunta(Long idPregunta);
    List<Respuestas> listarPorEstudiante(Long idEstudiante);
}
