package com.example.notas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesoresDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String email;
}
