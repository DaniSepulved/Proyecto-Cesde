package com.example.notas.model;

import java.util.List;

import jakarta.persistence.CascadeType;
// import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Preguntas") // <- Cambiado de "Cuestionarios" a "Preguntas"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preguntas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;

    @Column(nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    private Cuestionarios cuestionario;

    @OneToMany(mappedBy = "preguntas", cascade = CascadeType.ALL)
    private List<Respuestas> respuestas;
}
