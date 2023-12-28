package com.devsu.finanzas.ejercicio.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cliente")
@EqualsAndHashCode(callSuper=false)
public class Cliente extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "clienteId", unique = true)
    private Integer clienteId;
    @NotBlank
    @NotNull
    @Column(name = "contrasena")
    private String contrasena;
    @NotNull
    @Column(name = "estado")
    private Boolean estado;

}
