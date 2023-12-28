package com.devsu.finanzas.ejercicio.service.interfaces;

import java.util.List;

import com.devsu.finanzas.ejercicio.dto.ClienteDTO;
import com.devsu.finanzas.ejercicio.model.Cliente;

public interface ClienteService {
    public List<Cliente> listarClientes();
    public Cliente buscarCliente(Integer idCliente);
    public Cliente crearCliente(ClienteDTO nuevoClienteDTO);
    public Cliente actualizarCliente(Integer idCliente, ClienteDTO cliente);
    public void eliminarCliente(Integer idCliente);
}