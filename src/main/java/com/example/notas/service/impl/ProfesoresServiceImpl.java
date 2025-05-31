package com.example.notas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.notas.dto.ProfesoresDTO;
import com.example.notas.model.Profesores;
import com.example.notas.repository.ProfesoresRepository;
import com.example.notas.service.ProfesoresService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesoresServiceImpl implements ProfesoresService {

    private final ProfesoresRepository profesoresRepository;

    public ProfesoresServiceImpl(ProfesoresRepository profesoresRepository) {
        this.profesoresRepository = profesoresRepository;
    }

    @Override
    public Profesores crear(ProfesoresDTO dto) {
        Profesores profesor = Profesores.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .build();
        return profesoresRepository.save(profesor);
    }

    @Override
    public List<Profesores> listar() {
        return profesoresRepository.findAll();
    }

    @Override
    public Profesores buscarPorId(Long idProfesor) {
        return profesoresRepository.findById(idProfesor)
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con id: " + idProfesor));
    }

    @Override
    public Profesores actualizar(Long idProfesor, ProfesoresDTO dto) {
        Profesores profesor = buscarPorId(idProfesor); 
        profesor.setNombre(dto.getNombre());
        profesor.setEmail(dto.getEmail());
        return profesoresRepository.save(profesor);
    }

    @Override
    public void eliminar(Long idProfesor) {
        Profesores profesor = buscarPorId(idProfesor); 
        profesoresRepository.delete(profesor);
    }
}