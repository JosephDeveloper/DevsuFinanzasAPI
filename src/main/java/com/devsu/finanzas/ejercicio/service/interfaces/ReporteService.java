package com.devsu.finanzas.ejercicio.service.interfaces;

import com.devsu.finanzas.ejercicio.dto.ReporteDataDTO;

public interface ReporteService {
    public ReporteDataDTO generarReporte(String idCliente, String fechaInicio, String fechaFin);
}