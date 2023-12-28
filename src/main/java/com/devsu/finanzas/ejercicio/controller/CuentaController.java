package com.devsu.finanzas.ejercicio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.finanzas.ejercicio.dto.CuentaDTO;
import com.devsu.finanzas.ejercicio.model.Cuenta;
import com.devsu.finanzas.ejercicio.service.interfaces.CuentaService;
import com.devsu.finanzas.ejercicio.util.GeneralBodyResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/cuentas")
public class CuentaController {

    private CuentaService cService;

    @GetMapping
    public ResponseEntity<GeneralBodyResponse<List<Cuenta>>> listaCuenta() {
        List<Cuenta> response = cService.listarCuentas();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralBodyResponse<>(null, HttpStatus.NOT_FOUND.value()));
        } else {
            return ResponseEntity.ok(new GeneralBodyResponse<>(response, HttpStatus.OK.value()));
        }
    }

    @PostMapping
    public ResponseEntity<GeneralBodyResponse<Cuenta>> crearCuenta(@Valid @RequestBody CuentaDTO nuevaCuentaDTO) {
        Cuenta nuevaCuenta = cService.crearCuenta(nuevaCuentaDTO);
        return ResponseEntity.ok(new GeneralBodyResponse<>(nuevaCuenta, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{idCuenta}")
    public ResponseEntity<GeneralBodyResponse<Cuenta>> actualizarCuenta(@PathVariable Integer idCuenta,
            @Valid @RequestBody CuentaDTO cuentaDTO) {
        Cuenta response = cService.actualizarCuenta(idCuenta, cuentaDTO);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GeneralBodyResponse<>(null, HttpStatus.NOT_FOUND.value()));
        } else {
            return ResponseEntity.ok(new GeneralBodyResponse<>(response, HttpStatus.OK.value()));
        }
    }

    @DeleteMapping("/{idCuenta}")
    public void eliminarCuenta(@PathVariable Integer idCuenta) {
        cService.eliminarCuenta(idCuenta);
    }
    
}
