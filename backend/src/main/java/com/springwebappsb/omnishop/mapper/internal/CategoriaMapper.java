package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.CategoriaDto;
import com.springwebappsb.omnishop.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDto toDto(Categoria entity) {
        return CategoriaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    public Categoria toEntity(CategoriaDto dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }

}
