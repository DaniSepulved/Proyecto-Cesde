package com.example.notas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.notas.dto.PreguntasDTO;
import com.example.notas.model.Preguntas;
import com.example.notas.model.Cuestionarios;
import com.example.notas.repository.PreguntasRepository;
import com.example.notas.repository.CuestionariosRepository; 
import com.example.notas.service.PreguntasService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PreguntasServiceImpl implements PreguntasService {

    private final PreguntasRepository preguntasRepository;
    private final CuestionariosRepository cuestionariosRepository; 
    public PreguntasServiceImpl(PreguntasRepository preguntasRepository,
                                CuestionariosRepository cuestionariosRepository) {
        this.preguntasRepository = preguntasRepository;
        this.cuestionariosRepository = cuestionariosRepository;
    }

    @Override
    public Preguntas crear(PreguntasDTO dto) {
        Cuestionarios cuestionario = cuestionariosRepository.findById(dto.getId_cuestionario())
                .orElseThrow(() -> new EntityNotFoundException("Cuestionario no encontrado con id: " + dto.getId_cuestionario()));

        Preguntas pregunta = Preguntas.builder()
                .texto(dto.getTexto())
                .cuestionario(cuestionario)
                .build();
        return preguntasRepository.save(pregunta);
    }

    @Override
    public List<Preguntas> listar() {
        return preguntasRepository.findAll();
    }

    @Override
    public Preguntas buscarPorId(Long idPregunta) {
        return preguntasRepository.findById(idPregunta)
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada con id: " + idPregunta));
    }

    @Override
    public Preguntas actualizar(Long idPregunta, PreguntasDTO dto) {
        Preguntas pregunta = buscarPorId(idPregunta);

        Cuestionarios cuestionario = cuestionariosRepository.findById(dto.getId_cuestionario())
                .orElseThrow(() -> new EntityNotFoundException("Cuestionario no encontrado con id: " + dto.getId_cuestionario()));
        
        pregunta.setTexto(dto.getTexto());
        pregunta.setCuestionario(cuestionario); 
        return preguntasRepository.save(pregunta);
    }

    @Override
    public void eliminar(Long idPregunta) {
        Preguntas pregunta = buscarPorId(idPregunta);
        preguntasRepository.delete(pregunta);
    }

    @Override
    public List<Preguntas> listarPorCuestionario(Long idCuestionario) {
        if (!cuestionariosRepository.existsById(idCuestionario)) {
            throw new EntityNotFoundException("Cuestionario no encontrado con id: " + idCuestionario);
        }
        return preguntasRepository.findByCuestionarioIdCuestionario(idCuestionario);
    }
}
