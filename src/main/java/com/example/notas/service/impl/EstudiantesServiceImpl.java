package com.example.notas.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.notas.dto.EstudiantesDTO;
import com.example.notas.model.Estudiantes;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.service.EstudiantesService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstudiantesServiceImpl implements EstudiantesService {

    private  final EstudiantesRepository estudiantesRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Estudiantes crear(EstudiantesDTO dto) {
        Estudiantes estudiantes = Estudiantes.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .build();

        return estudiantesRepository.save(estudiantes);
    }
}
