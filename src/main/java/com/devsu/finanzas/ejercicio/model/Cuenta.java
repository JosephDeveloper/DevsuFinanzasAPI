package com.devsu.finanzas.ejercicio.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cuenta")
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotBlank
    @NotNull
    @Column(name = "numeroCuenta", unique = true)
    private String numeroCuenta;
    @NotBlank
    @NotNull
    @Column(name = "tipoCuenta")
    private String tipoCuenta;
    @NotNull
    @Column(name = "saldoInicial")
    private Double saldoInicial;
    @NotNull
    @Column(name = "estado")
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente clienteId;
}