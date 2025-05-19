package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.NoticiaDto;
import com.springwebappsb.omnishop.dto.request.NoticiaRequestDto;
import com.springwebappsb.omnishop.dto.response.NoticiaResponseDto;
import com.springwebappsb.omnishop.dto.update.NoticiaUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class NoticiaRestMapper {
    public NoticiaDto fromRequest(NoticiaRequestDto req) {
        return NoticiaDto.builder()
                .titulo(req.getTitulo())
                .contenido(req.getContenido())
                .imagenUrl(req.getImagenUrl())
                .fechaPublicacion(req.getFechaPublicacion())
                .build();
    }

    public NoticiaDto fromUpdate(NoticiaUpdateDto upd) {
        return NoticiaDto.builder()
                .id(upd.getId())
                .titulo(upd.getTitulo())
                .contenido(upd.getContenido())
                .imagenUrl(upd.getImagenUrl())
                .fechaPublicacion(upd.getFechaPublicacion())
                .build();
    }

    public NoticiaResponseDto toResponse(NoticiaDto dto) {
        return NoticiaResponseDto.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .contenido(dto.getContenido())
                .imagenUrl(dto.getImagenUrl())
                .fechaPublicacion(dto.getFechaPublicacion())
                .build();
    }
}
