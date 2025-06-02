package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.dto.request.PagoRequestDto;
import com.springwebappsb.omnishop.entity.Venta;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VentaService {
    VentaDto crear(VentaDto dto);
    VentaDto actualizar(VentaDto dto);
    void eliminar(Long id);
    VentaDto obtener(Long id);
    List<VentaDto> listar();
    void actualizarCantidadEnCarrito(Long usuarioId, Long productoId, int cantidad);

    Venta crearVentaPendiente(PagoRequestDto dto, Authentication auth);

    void marcarVentaComoPagada(Long ventaId);

    Venta crearVentaPagadaSimulada(PagoRequestDto requestDto, Authentication auth);
}
