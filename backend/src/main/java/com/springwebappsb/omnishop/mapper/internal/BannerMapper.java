package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.BannerDto;
import com.springwebappsb.omnishop.entity.Banner;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {
    public BannerDto toDto(Banner b) {
        return BannerDto.builder()
                .id(b.getId())
                .titulo(b.getTitulo())
                .imagenUrl(b.getImagenUrl())
                .enlaceDestino(b.getEnlaceDestino())
                .activo(b.getActivo())
                .build();
    }

    public Banner toEntity(BannerDto dto) {
        return Banner.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .imagenUrl(dto.getImagenUrl())
                .enlaceDestino(dto.getEnlaceDestino())
                .activo(dto.getActivo())
                .build();
    }
}
