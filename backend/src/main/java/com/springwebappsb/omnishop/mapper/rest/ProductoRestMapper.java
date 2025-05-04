package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.ProductoDto;
import com.springwebappsb.omnishop.dto.request.ProductoRequestDto;
import com.springwebappsb.omnishop.dto.response.ProductoResponseDto;
import com.springwebappsb.omnishop.dto.update.ProductoUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class ProductoRestMapper {


    public ProductoDto fromRequest(ProductoRequestDto productoRequestDto){

        return ProductoDto.builder()
                .nombre(productoRequestDto.getNombre())
                .precio(productoRequestDto.getPrecio())
                .descripcion(productoRequestDto.getDescripcion())
                .imagen(productoRequestDto.getImagen())
                .build();
    }

    public ProductoDto fromUpdate(ProductoUpdateDto productoUpdateDto){

        return ProductoDto.builder()
                .id(productoUpdateDto.getId())
                .nombre(productoUpdateDto.getNombre())
                .precio(productoUpdateDto.getPrecio())
                .descripcion(productoUpdateDto.getDescripcion())
                .imagen(productoUpdateDto.getImagen())
                .build();
    }


    public ProductoResponseDto toResponse(ProductoDto productoDto){

        return ProductoResponseDto.builder()
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .descripcion(productoDto.getDescripcion())
                .imagen(productoDto.getImagen())
                .categoriaId(productoDto.getCategoria() !=null? productoDto.getCategoria().getId() : null)
                .build();
    }

}
