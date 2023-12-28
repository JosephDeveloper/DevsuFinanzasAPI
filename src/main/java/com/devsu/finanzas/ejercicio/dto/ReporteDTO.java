package com.devsu.finanzas.ejercicio.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteDTO {
    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private Double movimiento;
    private Double saldoDisponible;
}
