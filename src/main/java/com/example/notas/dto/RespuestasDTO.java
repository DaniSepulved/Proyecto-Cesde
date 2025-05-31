package com.example.notas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestasDTO {
    @NotNull
    private Long id_pregunta;

    @NotNull
    private Long id_estudiante;
}
