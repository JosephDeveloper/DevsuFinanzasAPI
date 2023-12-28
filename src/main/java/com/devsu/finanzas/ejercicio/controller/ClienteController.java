package com.devsu.finanzas.ejercicio.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
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

import com.devsu.finanzas.ejercicio.dto.ClienteDTO;
import com.devsu.finanzas.ejercicio.model.Cliente;
import com.devsu.finanzas.ejercicio.service.interfaces.ClienteService;
import com.devsu.finanzas.ejercicio.util.GeneralBodyResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService uService;

    @GetMapping
    public ResponseEntity<GeneralBodyResponse<List<Cliente>>> listaCliente() {
        List<Cliente> response = uService.listarClientes();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GeneralBodyResponse<>(null, HttpStatus.NOT_FOUND.value()));
        } else {
            return ResponseEntity.ok(new GeneralBodyResponse<>(response, HttpStatus.OK.value()));
        }
    }

    @PostMapping
    public ResponseEntity<GeneralBodyResponse<Cliente>> crearCliente(@Valid @RequestBody ClienteDTO nuevoClienteDTO) {
        Cliente nuevoCliente = uService.crearCliente(nuevoClienteDTO);
        return ResponseEntity.ok(new GeneralBodyResponse<>(nuevoCliente, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<GeneralBodyResponse<Cliente>> actualizarCliente(@PathVariable Integer idCliente,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente response = uService.actualizarCliente(idCliente, clienteDTO);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GeneralBodyResponse<>(null, HttpStatus.NOT_FOUND.value()));
        } else {
            return ResponseEntity.ok(new GeneralBodyResponse<>(response, HttpStatus.OK.value()));
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer idCliente) {
        try {
            uService.eliminarCliente(idCliente);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se puede eliminar el cliente, tiene cuentas asociadas");
        }
        return null;
    }
}
