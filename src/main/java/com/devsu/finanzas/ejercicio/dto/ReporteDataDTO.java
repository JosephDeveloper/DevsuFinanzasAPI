package com.devsu.finanzas.ejercicio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDataDTO {
    private List<ReporteDTO> reporte;
    private String pdfBase64;
}
