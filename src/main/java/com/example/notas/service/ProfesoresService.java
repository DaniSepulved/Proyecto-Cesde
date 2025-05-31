package com.example.notas.service;

import java.util.List;
import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;

public interface ProfesoresService {
    Profesores crear(ProfesoresDTO dto);
    List<Profesores> listar();
    Profesores buscarPorId(Long idProfesor);
    Profesores actualizar(Long idProfesor, ProfesoresDTO dto);
    void eliminar(Long idProfesor);
}
