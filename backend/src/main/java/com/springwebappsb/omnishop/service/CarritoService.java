package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.VentaDto;

public interface CarritoService {
    VentaDto obtenerCarritoActivo(Long usuarioId);
    VentaDto agregarProductoAlCarrito(Long usuarioId, Long productoId, int cantidad);
    VentaDto quitarProductoDelCarrito(Long usuarioId, Long productoId);
    void vaciarCarrito(Long usuarioId);
}

