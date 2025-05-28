package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.ProductoDto;
import com.springwebappsb.omnishop.dto.request.ProductoRequestDto;
import com.springwebappsb.omnishop.dto.response.ProductoResponseDto;
import com.springwebappsb.omnishop.dto.update.ProductoUpdateDto;
import com.springwebappsb.omnishop.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoRestMapper {


    public ProductoDto fromRequest(ProductoRequestDto productoRequestDto){

        return ProductoDto.builder()
                .nombre(productoRequestDto.getNombre())
                .precio(productoRequestDto.getPrecio())
                .descripcion(productoRequestDto.getDescripcion())
                .imagen(productoRequestDto.getImagen())
                .destacado(productoRequestDto.isDestacado())
                .visible(productoRequestDto.isVisible())
                .build();
    }

    public ProductoDto fromUpdate(ProductoUpdateDto productoUpdateDto){
        Categoria categoria = new Categoria();
        categoria.setId(productoUpdateDto.getCategoriaId());

        return ProductoDto.builder()
                .id(productoUpdateDto.getId())
                .nombre(productoUpdateDto.getNombre())
                .precio(productoUpdateDto.getPrecio())
                .descripcion(productoUpdateDto.getDescripcion())
                .imagen(productoUpdateDto.getImagen())
                .destacado(productoUpdateDto.isDestacado())
                .visible(productoUpdateDto.isVisible())
                .categoria(categoria)
                .build();
    }


    public ProductoResponseDto toResponse(ProductoDto productoDto){

        return ProductoResponseDto.builder()
                .id(productoDto.getId())
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .descripcion(productoDto.getDescripcion())
                .imagen(productoDto.getImagen())
                .categoriaId(productoDto.getCategoria() !=null? productoDto.getCategoria().getId() : null)
                .destacado(productoDto.getDestacado())
                .visible(productoDto.getVisible())
                .build();
    }

}
