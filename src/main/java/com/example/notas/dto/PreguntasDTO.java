package com.example.notas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreguntasDTO {
    @NotBlank(message = "El texto de la pregunta no puede estar vacío")
    private String texto;

    @NotNull(message = "El ID del cuestionario no puede ser nulo")
    private Long id_cuestionario;
}
