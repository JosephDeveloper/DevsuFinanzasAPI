package com.devsu.finanzas.ejercicio.model;

import java.io.Serializable;
import java.util.Date;

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
import lombok.Data;

@Data
@Entity
@Table(name = "Movimientos")
public class Movimientos implements Serializable {
    private static final long serialVersionUID = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "fecha")
    private Date fecha;
    @NotBlank
    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;
    @NotNull
    @Column(name = "valor")
    private Double valor;
    @NotNull
    @Column(name = "saldo")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "numeroCuenta", referencedColumnName = "numeroCuenta")
    private Cuenta cuenta;
}
