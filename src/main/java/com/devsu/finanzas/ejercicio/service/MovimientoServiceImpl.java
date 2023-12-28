package com.devsu.finanzas.ejercicio.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.finanzas.ejercicio.dto.MovimientoDTO;
import com.devsu.finanzas.ejercicio.exception.BadRequestException;
import com.devsu.finanzas.ejercicio.exception.CustomException;
import com.devsu.finanzas.ejercicio.model.Cuenta;
import com.devsu.finanzas.ejercicio.model.Movimientos;
import com.devsu.finanzas.ejercicio.repository.CuentaRepository;
import com.devsu.finanzas.ejercicio.repository.MovimientoRepository;
import com.devsu.finanzas.ejercicio.service.interfaces.CuentaService;
import com.devsu.finanzas.ejercicio.service.interfaces.MovimientoService;
import com.devsu.finanzas.ejercicio.util.ConstantsUtil;
import com.devsu.finanzas.ejercicio.util.UtilDate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovimientoServiceImpl implements MovimientoService, ConstantsUtil {

    private UtilDate uDate;
    private CuentaService cService;
    private CuentaRepository cRepository;
    private MovimientoRepository mRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Movimientos> listarMovimientos() {
        return Optional.of(mRepository.findAll())
                .orElseThrow(() -> new CustomException("Error al listar movimientos"));
    }

    @Override
    public void realizarMovimiento(MovimientoDTO movimientoDto) throws ParseException {
        Cuenta cuenta = cService.buscarCuentaPorNumero(movimientoDto.getNumeroCuenta());

        if (esMovimientoInvalido(movimientoDto, cuenta)) {
            throw new BadRequestException("Saldo no disponible o cupo diario excedido");
        }

        realizarMovimientoYActualizarSaldo(cuenta, movimientoDto);
    }

    private boolean esMovimientoInvalido(MovimientoDTO movimientoDto, Cuenta cuenta) throws ParseException {
        boolean esAhorro = movimientoDto.getTipo().equals("Ahorros");
        double saldoInicial = cuenta.getSaldoInicial();

        //Validar si hay saldo para el valor del retiro
        if (esAhorro && saldoInicial < movimientoDto.getMovimiento()) {
            return true;
        }
        
        Date fechaParametrizada = uDate.stringToDate(movimientoDto.getFecha());
        double totalMovimientosEnFecha = calcularTotalMovimientosEnFecha(cuenta.getNumeroCuenta(), fechaParametrizada);
        
        //Validar si los retiros realizados en la fecha superan lo permitido
        if (esAhorro && (totalMovimientosEnFecha + movimientoDto.getMovimiento()) > 1000) {
            return true;
        }

        return false;
    }

    private double calcularTotalMovimientosEnFecha(String numeroCuenta, Date fecha) throws ParseException {
        List<Movimientos> movimientos = mRepository.findByCuentaNumeroCuentaAndFecha(numeroCuenta,
                uDate.dateToFormat(fecha, YYYY_MM_DD));

        return movimientos.stream()
                .mapToDouble(Movimientos::getValor)
                .sum();
    }

    private void realizarMovimientoYActualizarSaldo(Cuenta cuenta, MovimientoDTO movimientoDto) throws ParseException {
        Date fecha = uDate.stringToDate(movimientoDto.getFecha());
        double movimientoValor = movimientoDto.getMovimiento();
        double saldoMovimiento = (movimientoDto.getTipo().equals("Corriente")) ? movimientoValor : -movimientoValor;
        double nuevoSaldo = cuenta.getSaldoInicial() + saldoMovimiento;

        Movimientos movimiento = new Movimientos();
        movimiento.setFecha(fecha);
        movimiento.setTipoMovimiento(movimientoDto.getTipo());
        movimiento.setValor(movimientoValor);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        mRepository.save(movimiento);

        cuenta.setSaldoInicial(nuevoSaldo);
        cRepository.save(cuenta);
    }

}