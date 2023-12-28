package com.devsu.finanzas.ejercicio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    @NotBlank
    @NotNull
    private String fecha;
    @NotBlank
    @NotNull
    private String numeroCuenta;
    @NotBlank
    @NotNull
    private String tipo;
    @NotNull
    private double movimiento;
}