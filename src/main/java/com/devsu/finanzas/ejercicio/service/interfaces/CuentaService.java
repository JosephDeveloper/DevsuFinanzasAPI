package com.devsu.finanzas.ejercicio.service.interfaces;

import java.util.List;

import com.devsu.finanzas.ejercicio.dto.CuentaDTO;
import com.devsu.finanzas.ejercicio.model.Cuenta;

public interface CuentaService {
    public List<Cuenta> listarCuentas();
    public Cuenta crearCuenta(CuentaDTO nuevoCuentaDTO);
    public Cuenta actualizarCuenta(Integer idCuenta, CuentaDTO nuevoCuentaDTO);
    public void eliminarCuenta(Integer idCuenta);
    public Cuenta buscarCuentaPorNumero(String numeroCuenta);
}