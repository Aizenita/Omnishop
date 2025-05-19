package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.NoticiaDto;
import com.springwebappsb.omnishop.entity.Noticia;
import org.springframework.stereotype.Component;

@Component
public class NoticiaMapper {
    public NoticiaDto toDto(Noticia n) {
        return NoticiaDto.builder()
                .id(n.getId())
                .titulo(n.getTitulo())
                .contenido(n.getContenido())
                .imagenUrl(n.getImagenUrl())
                .fechaPublicacion(n.getFechaPublicacion())
                .build();
    }

    public Noticia toEntity(NoticiaDto dto) {
        return Noticia.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .contenido(dto.getContenido())
                .imagenUrl(dto.getImagenUrl())
                .fechaPublicacion(dto.getFechaPublicacion())
                .build();
    }
}
