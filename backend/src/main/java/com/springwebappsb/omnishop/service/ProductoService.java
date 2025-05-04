package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.ProductoDto;

import java.util.List;

public interface ProductoService {

    ProductoDto crearProducto(ProductoDto dto, Long categoriaId);
    ProductoDto actualizarProducto(ProductoDto dto, Long categoriaId);
    void eliminarProducto(Long id);
    ProductoDto obtenerProducto(Long id);
    List<ProductoDto> listarProductos();
}


