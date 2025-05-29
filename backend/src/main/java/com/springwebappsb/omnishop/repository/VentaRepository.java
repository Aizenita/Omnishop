package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.Venta;
import com.springwebappsb.omnishop.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findByUsuarioIdAndEstadoVenta(Long usuarioId, EstadoVenta estadoVenta);
}

