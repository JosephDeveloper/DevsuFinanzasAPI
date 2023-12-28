package com.devsu.finanzas.ejercicio.service.interfaces;

import java.text.ParseException;
import java.util.List;

import com.devsu.finanzas.ejercicio.dto.MovimientoDTO;
import com.devsu.finanzas.ejercicio.model.Movimientos;

public interface MovimientoService {
    public List<Movimientos> listarMovimientos();
    public void realizarMovimiento(MovimientoDTO movimientoDto) throws ParseException;
}