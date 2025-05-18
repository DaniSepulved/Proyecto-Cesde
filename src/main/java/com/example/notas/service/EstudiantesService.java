package com.example.notas.service;

import java.util.List;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;

public interface EstudiantesService {
    Estudiantes crear(EstudiantesDTO dto);
    List<Estudiantes> listar();
    Estudiantes buscarPorId(Long id_estudiante);
    Estudiantes actualizar(Long id_estudiante, EstudiantesDTO dto);
    void eliminar(Long id_estudiante);
}
