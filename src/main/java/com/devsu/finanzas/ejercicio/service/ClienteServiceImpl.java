package com.devsu.finanzas.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.finanzas.ejercicio.dto.ClienteDTO;
import com.devsu.finanzas.ejercicio.exception.CustomException;
import com.devsu.finanzas.ejercicio.exception.BadRequestException;
import com.devsu.finanzas.ejercicio.model.Cliente;
import com.devsu.finanzas.ejercicio.repository.ClienteRepository;
import com.devsu.finanzas.ejercicio.service.interfaces.ClienteService;
import com.devsu.finanzas.ejercicio.util.ModelMapperUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private ModelMapperUtil mapperUtil;
    private ClienteRepository cRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return Optional.of(cRepository.findAll())
                .orElseThrow(() -> new CustomException("Error al listar clientes"));
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarCliente(Integer idCliente) {
        return cRepository.findByClienteId(idCliente)
                .orElseThrow(() -> new BadRequestException("Cliente no encontrado"));
    }

    @Override
    public Cliente crearCliente(ClienteDTO nuevoClienteDTO) {
        return Optional.of(nuevoClienteDTO)
                .map(dto -> mapperUtil.convertToEntity(dto, Cliente.class))
                .map(cRepository::save)
                .orElseThrow(() -> new CustomException("Error al crear cliente"));
    }

    @Override
    public Cliente actualizarCliente(Integer idCliente, ClienteDTO clienteDTO) {
        Cliente cliente = mapperUtil.convertToEntity(clienteDTO, Cliente.class);
        cRepository.findById(idCliente)
                .orElseThrow(() -> new BadRequestException("Cliente no encontrado"));
        cliente.setId(idCliente);
        return Optional.of(cliente)
                .map(cRepository::save)
                .orElseThrow(() -> new CustomException("Error al actualizar cliente"));
    }

    @Override
    public void eliminarCliente(Integer idCliente) {
        cRepository.findById(idCliente)
                .ifPresentOrElse(
                        cliente -> cRepository.deleteById(idCliente),
                        () -> {
                            throw new BadRequestException("No existe cliente con id " + idCliente);
                        });
    }

}
