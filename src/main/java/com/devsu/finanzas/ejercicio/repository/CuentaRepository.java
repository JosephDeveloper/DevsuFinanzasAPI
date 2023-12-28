package com.devsu.finanzas.ejercicio.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.finanzas.ejercicio.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    public Optional<Cuenta> findById(Integer idCuenta);
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
