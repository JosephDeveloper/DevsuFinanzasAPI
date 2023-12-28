package com.devsu.finanzas.ejercicio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
    @NotBlank
    @NotNull
    private String nombre;
    @NotBlank
    @NotNull
    private String genero;
    @NotNull
    private Integer edad;
    @NotBlank
    @NotNull
    private String identificacion;
    @NotBlank
    @NotNull
    private String direccion;
    @NotBlank
    @NotNull
    private String telefono;
    @NotNull
    private Integer clienteId;
    @NotBlank
    @NotNull
    private String contrasena;
    @NotNull
    private Boolean estado;
}
