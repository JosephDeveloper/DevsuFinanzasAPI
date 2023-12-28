package com.devsu.finanzas.ejercicio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaDTO {
    @NotBlank
    @NotNull
    private String numeroCuenta;
    @NotBlank
    @NotNull
    private String tipoCuenta;
    @NotNull
    private double saldoInicial;
    @NotNull
    private boolean estado;
    @NotNull
    private Integer clienteId;
}