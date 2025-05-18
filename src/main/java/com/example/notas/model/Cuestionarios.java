package com.example.notas.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "Cuestionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuestionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cuestionario;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesores profesores;

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL)
    private List<Preguntas> preguntas;
}
