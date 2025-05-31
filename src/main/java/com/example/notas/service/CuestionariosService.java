package com.example.notas.service;

import java.util.List;
import com.example.notas.dto.CuestionariosDTO;
import com.example.notas.model.Cuestionarios;

public interface CuestionariosService {
    Cuestionarios crear(CuestionariosDTO dto);
    List<Cuestionarios> listar();
    Cuestionarios buscarPorId(Long idCuestionario);
    Cuestionarios actualizar(Long idCuestionario, CuestionariosDTO dto);
    void eliminar(Long idCuestionario);
    List<Cuestionarios> listarPorProfesor(Long idProfesor);
}
