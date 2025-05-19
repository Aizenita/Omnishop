package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.CategoriaDto;
import com.springwebappsb.omnishop.dto.request.CategoriaRequestDto;
import com.springwebappsb.omnishop.dto.response.CategoriaResponseDto;
import com.springwebappsb.omnishop.dto.update.CategoriaUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class CategoriaRestMapper {

    public CategoriaDto fromRequest(CategoriaRequestDto req) {
        return CategoriaDto.builder()
                .nombre(req.getNombre())
                .build();
    }

    public CategoriaDto fromUpdate(CategoriaUpdateDto upd) {
        return CategoriaDto.builder()
                .id(upd.getId())
                .nombre(upd.getNombre())
                .build();
    }

    public CategoriaResponseDto toResponse(CategoriaDto dto) {
        return CategoriaResponseDto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }



}
