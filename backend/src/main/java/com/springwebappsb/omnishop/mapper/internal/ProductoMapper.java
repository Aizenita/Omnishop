package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.ProductoDto;
import com.springwebappsb.omnishop.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDto ProductotoDto(Producto producto) {

        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .categoria(producto.getCategoria())
                .descripcion(producto.getDescripcion())
                .imagen(producto.getImagen())
                .build();
    }


    public Producto ProductoDtoToProducto(ProductoDto productoDto) {

        return Producto.builder()
                .id(productoDto.getId())
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .categoria(productoDto.getCategoria())
                .descripcion(productoDto.getDescripcion())
                .imagen(productoDto.getImagen())
                .build();
    }

}
