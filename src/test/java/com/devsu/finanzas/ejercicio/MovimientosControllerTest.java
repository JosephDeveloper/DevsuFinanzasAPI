package com.devsu.finanzas.ejercicio;

import com.devsu.finanzas.ejercicio.controller.MovimientoController;
import com.devsu.finanzas.ejercicio.dto.MovimientoDTO;
import com.devsu.finanzas.ejercicio.exception.CustomException;
import com.devsu.finanzas.ejercicio.model.Cliente;
import com.devsu.finanzas.ejercicio.model.Cuenta;
import com.devsu.finanzas.ejercicio.service.interfaces.CuentaService;
import com.devsu.finanzas.ejercicio.service.interfaces.MovimientoService;
import com.devsu.finanzas.ejercicio.util.GeneralBodyResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class MovimientosControllerTest {
    @InjectMocks
    private MovimientoController mController;

    @Mock
    private MovimientoService mService;

    @Mock
    private CuentaService cService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarMovimientoExitoso() throws ParseException {
        // Simula un DTO de movimiento exitoso
        MovimientoDTO movimientoDto = new MovimientoDTO();
        movimientoDto.setNumeroCuenta("cuenta456");
        movimientoDto.setTipo("Ahorros");
        movimientoDto.setMovimiento(50.0);
        movimientoDto.setFecha("2023-11-02 18:00:00");

        // Simula el comportamiento del servicio para una cuenta válida
        Mockito.when(cService.buscarCuentaPorNumero("cuenta456")).thenReturn(
                new Cuenta((Integer) 1, "cuenta456", "Ahorros", 100.0, true, new Cliente(1, "prueba", true)));

        // Llama al método del controlador
        ResponseEntity<GeneralBodyResponse<String>> response = mController.realizarMovimiento(movimientoDto);
        // Verifica que la respuesta sea un ResponseEntity con el código de estado 200
        // (OK)
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    public void testRealizarMovimientoSaldoInsuficiente() throws ParseException {
        // Simula un DTO de movimiento con saldo insuficiente
        MovimientoDTO movimientoDto = new MovimientoDTO();
        movimientoDto.setNumeroCuenta("cuenta456");
        movimientoDto.setTipo("Ahorros");
        movimientoDto.setMovimiento(300.0);
        movimientoDto.setFecha("2023-11-02 18:00:00");

        // Simula el comportamiento del servicio para saldo insuficiente
        Mockito.when(cService.buscarCuentaPorNumero("cuenta456")).thenReturn(
                new Cuenta((Integer) 1, "cuenta456", "Ahorros", 100.0, true, new Cliente(1, "prueba", true)));

        try {
            // Llama al método del controlador y espera una excepción
            mController.realizarMovimiento(movimientoDto);
        } catch (CustomException e) {
            // Verifica que la excepción arrojada tenga el mensaje esperado
            assertEquals("Saldo no disponible o cupo diario excedido", e.getMessage());
        }
    }
}