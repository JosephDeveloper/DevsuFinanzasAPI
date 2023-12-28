package com.devsu.finanzas.ejercicio.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.finanzas.ejercicio.dto.ReporteDataDTO;
import com.devsu.finanzas.ejercicio.service.interfaces.ReporteService;
import com.devsu.finanzas.ejercicio.util.GeneralBodyResponse;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/reportes")
public class ReporteController {

    private ReporteService rService;

    @GetMapping
    public ResponseEntity<GeneralBodyResponse<ReporteDataDTO>> generarReporte(@RequestParam String idCliente,
            @RequestParam String fechaInicio, @RequestParam String fechaFin) throws IOException {

        ReporteDataDTO reporte = rService.generarReporte(idCliente, fechaInicio, fechaFin);
        return ResponseEntity.ok(new GeneralBodyResponse<>(reporte, HttpStatus.OK.value()));
    }

}
