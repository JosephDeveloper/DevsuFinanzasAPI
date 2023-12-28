package com.devsu.finanzas.ejercicio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
@MappedSuperclass
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @NotNull
    @Column(name = "nombre")
    private String nombre;
    @NotBlank
    @NotNull
    @Column(name = "genero")
    private String genero;
    @NotNull
    @Column(name = "edad")
    private Integer edad;
    @NotBlank
    @NotNull
    @Column(name = "identificacion")
    private String identificacion;
    @NotBlank
    @NotNull
    @Column(name = "direccion")
    private String direccion;
    @NotBlank
    @NotNull
    @Column(name = "telefono")
    private String telefono;
}