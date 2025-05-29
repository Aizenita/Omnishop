package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.VentaDto;

import java.util.List;

public interface VentaService {
    VentaDto crear(VentaDto dto);
    VentaDto actualizar(VentaDto dto);
    void eliminar(Long id);
    VentaDto obtener(Long id);
    List<VentaDto> listar();
    void actualizarCantidadEnCarrito(Long usuarioId, Long productoId, int cantidad);

}
