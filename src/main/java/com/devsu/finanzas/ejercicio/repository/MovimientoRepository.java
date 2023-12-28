package com.devsu.finanzas.ejercicio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsu.finanzas.ejercicio.model.Movimientos;

public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {
    @Query(value = "SELECT * " +
                    "FROM movimientos " +
                    "WHERE numero_cuenta = ?1 " +
                    "AND DATE(fecha) = DATE(?2)", nativeQuery = true)
    List<Movimientos> findByCuentaNumeroCuentaAndFecha(String numeroCuenta, Date fecha);
    
    @Query(value = "SELECT m.id, m.fecha, m.numero_cuenta, m.tipo_movimiento, m.valor, m.saldo " +
                    "FROM movimientos m " +
                    "INNER JOIN cuenta c ON m.numero_cuenta = c.numero_cuenta " +
                    "INNER JOIN cliente cli ON c.cliente_id = cli.id " +
                    "WHERE cli.identificacion = ?1 " +
                    "AND DATE(m.fecha) BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Movimientos> listarReporte(String idCliente, String fechaInicio, String fechaFin);

}