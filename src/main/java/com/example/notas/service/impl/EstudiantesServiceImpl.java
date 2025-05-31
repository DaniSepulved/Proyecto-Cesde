package com.example.notas.service.impl;

import java.util.List;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.service.EstudiantesService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class EstudiantesServiceImpl implements EstudiantesService {

    private final EstudiantesRepository estudiantesRepository;

    public EstudiantesServiceImpl(EstudiantesRepository estudiantesRepository) {
        this.estudiantesRepository = estudiantesRepository;
    }

    @Override
    public Estudiantes crear(EstudiantesDTO dto) {
        Estudiantes estudiante = Estudiantes.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .build();

        return estudiantesRepository.save(estudiante);
    }

    @Override
    public Estudiantes buscarPorId(Long id) {
        return estudiantesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));
    }

    @Override
    public List<Estudiantes> listar() {
        return estudiantesRepository.findAll();
    }

    @Override
    public Estudiantes actualizar(Long id, EstudiantesDTO dto) {
        Estudiantes estudiante = estudiantesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());

        return estudiantesRepository.save(estudiante);
    }

    @Override
    public void eliminar(Long id) {
        Estudiantes estudiante = estudiantesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        estudiantesRepository.delete(estudiante);
    }
}