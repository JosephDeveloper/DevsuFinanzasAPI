package com.devsu.finanzas.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.finanzas.ejercicio.dto.CuentaDTO;
import com.devsu.finanzas.ejercicio.exception.CustomException;
import com.devsu.finanzas.ejercicio.model.Cliente;
import com.devsu.finanzas.ejercicio.model.Cuenta;
import com.devsu.finanzas.ejercicio.repository.CuentaRepository;
import com.devsu.finanzas.ejercicio.service.interfaces.ClienteService;
import com.devsu.finanzas.ejercicio.service.interfaces.CuentaService;
import com.devsu.finanzas.ejercicio.util.ModelMapperUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private ModelMapperUtil mapperUtil;
    private CuentaRepository cRepository;
    private ClienteService cliService;

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> listarCuentas() {
        return Optional.of(cRepository.findAll())
                .orElseThrow(() -> new CustomException("Error al listar clientes"));
    }

    @Override
    public Cuenta crearCuenta(CuentaDTO nuevoCuentaDTO) {
        Cuenta nuevaCuenta = mapDTOToEntity(nuevoCuentaDTO);
        Cliente cliente = cliService.buscarCliente(nuevoCuentaDTO.getClienteId());
        nuevaCuenta.setClienteId(cliente);
        return Optional.of(nuevaCuenta)
                .map(cRepository::save)
                .orElseThrow(() -> new CustomException("Error al crear cuenta"));
    }

    @Override
    public Cuenta actualizarCuenta(Integer idCuenta, CuentaDTO nuevoCuentaDTO) {
        Cuenta cuenta = mapperUtil.convertToEntity(nuevoCuentaDTO, Cuenta.class);
        cRepository.findById(idCuenta)
                .orElseThrow(() -> new CustomException("Cuenta no encontrada"));
        cuenta.setId(idCuenta);
        return Optional.of(cuenta)
                .map(cRepository::save)
                .orElseThrow(() -> new CustomException("Error al actualizar cuenta"));
    }

    @Override
    public void eliminarCuenta(Integer idCuenta) {
        cRepository.findById(idCuenta)
                .ifPresentOrElse(
                        cuenta -> cRepository.deleteById(idCuenta),
                        () -> {
                            throw new CustomException("No existe cuenta con id " + idCuenta);
                        });
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        return cRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CustomException("Cuenta no encontrada"));
    }

    private Cuenta mapDTOToEntity(CuentaDTO cuentaDTO){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.isEstado());
        return cuenta;
    }

}
