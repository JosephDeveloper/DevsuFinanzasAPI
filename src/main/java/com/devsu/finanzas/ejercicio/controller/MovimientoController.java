package com.devsu.finanzas.ejercicio.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.finanzas.ejercicio.dto.MovimientoDTO;
import com.devsu.finanzas.ejercicio.model.Movimientos;
import com.devsu.finanzas.ejercicio.service.interfaces.MovimientoService;
import com.devsu.finanzas.ejercicio.util.GeneralBodyResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/movimientos")
public class MovimientoController {

    private MovimientoService mService;

    @PostMapping
    public ResponseEntity<GeneralBodyResponse<String>> realizarMovimiento(
            @Valid @RequestBody MovimientoDTO movimientoDTO) throws ParseException {
        mService.realizarMovimiento(movimientoDTO);
        return ResponseEntity.ok(new GeneralBodyResponse<>("Movimiento realizado con éxito", HttpStatus.OK.value()));
    }

    @GetMapping
    public ResponseEntity<GeneralBodyResponse<List<Movimientos>>> listaMovimiento() {
        List<Movimientos> response = mService.listarMovimientos();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GeneralBodyResponse<>(null, HttpStatus.NOT_FOUND.value()));
        } else {
            return ResponseEntity.ok(new GeneralBodyResponse<>(response, HttpStatus.OK.value()));
        }
    }

}
