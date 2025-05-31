package com.example.notas.service;

import java.util.List;
import com.example.notas.dto.PreguntasDTO;
import com.example.notas.model.Preguntas;

public interface PreguntasService {
    Preguntas crear(PreguntasDTO dto);
    List<Preguntas> listar();
    Preguntas buscarPorId(Long idPregunta);
    Preguntas actualizar(Long idPregunta, PreguntasDTO dto);
    void eliminar(Long idPregunta);
    List<Preguntas> listarPorCuestionario(Long idCuestionario); 
}
