package com.example.notas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.notas.dto.CuestionariosDTO;
import com.example.notas.model.Cuestionarios;
import com.example.notas.model.Profesores;
import com.example.notas.repository.CuestionariosRepository;
import com.example.notas.repository.ProfesoresRepository;
import com.example.notas.service.CuestionariosService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CuestionariosServiceImpl implements CuestionariosService {

    private final CuestionariosRepository cuestionariosRepository;
    private final ProfesoresRepository profesoresRepository;

    public CuestionariosServiceImpl(CuestionariosRepository cuestionariosRepository,
                                    ProfesoresRepository profesoresRepository) {
        this.cuestionariosRepository = cuestionariosRepository;
        this.profesoresRepository = profesoresRepository;
    }

    @Override
    public Cuestionarios crear(CuestionariosDTO dto) {
        Profesores profesor = profesoresRepository.findById(dto.getId_profesor())
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con id: " + dto.getId_profesor()));

        Cuestionarios cuestionario = Cuestionarios.builder()
                .titulo(dto.getTitulo())
                .profesor(profesor)
                .build();
        return cuestionariosRepository.save(cuestionario);
    }

    @Override
    public List<Cuestionarios> listar() {
        return cuestionariosRepository.findAll();
    }

    @Override
    public Cuestionarios buscarPorId(Long idCuestionario) {
        return cuestionariosRepository.findById(idCuestionario)
                .orElseThrow(() -> new EntityNotFoundException("Cuestionario no encontrado con id: " + idCuestionario));
    }

    @Override
    public Cuestionarios actualizar(Long idCuestionario, CuestionariosDTO dto) {
        Cuestionarios cuestionario = buscarPorId(idCuestionario);

        Profesores profesor = profesoresRepository.findById(dto.getId_profesor())
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con id: " + dto.getId_profesor()));

        cuestionario.setTitulo(dto.getTitulo());
        cuestionario.setProfesor(profesor);
        return cuestionariosRepository.save(cuestionario);
    }

    @Override
    public void eliminar(Long idCuestionario) {
        Cuestionarios cuestionario = buscarPorId(idCuestionario);
        cuestionariosRepository.delete(cuestionario);
    }

    @Override
    public List<Cuestionarios> listarPorProfesor(Long idProfesor) {
        if (!profesoresRepository.existsById(idProfesor)) {
            throw new EntityNotFoundException("Profesor no encontrado con id: " + idProfesor);
        }
        return cuestionariosRepository.findByProfesorIdProfesor(idProfesor);
    }
}