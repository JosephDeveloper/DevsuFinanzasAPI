package com.devsu.finanzas.ejercicio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.finanzas.ejercicio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Optional<Cliente> findById(Integer idCliente);
    Optional<Cliente> findByClienteId(Integer clienteId);
}
