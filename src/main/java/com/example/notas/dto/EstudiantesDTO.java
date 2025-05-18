package com.example.notas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstudiantesDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String email;
}
