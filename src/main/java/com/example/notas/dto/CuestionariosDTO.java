package com.example.notas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuestionariosDTO {
    @NotBlank
    private String titulo;

    @NotNull
    private Long id_profesor;
}
