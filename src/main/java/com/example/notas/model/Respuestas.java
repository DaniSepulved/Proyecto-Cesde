package com.example.notas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Respuestas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respuestas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_respuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPregunta", referencedColumnName = "idPregunta", nullable = false)
    private Preguntas preguntas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante", nullable = false)
    private Estudiantes estudiantes;
}