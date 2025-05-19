package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.BannerDto;
import com.springwebappsb.omnishop.dto.request.BannerRequestDto;
import com.springwebappsb.omnishop.dto.response.BannerResponseDto;
import com.springwebappsb.omnishop.dto.update.BannerUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class BannerRestMapper {
    public BannerDto fromRequest(BannerRequestDto req) {
        return BannerDto.builder()
                .titulo(req.getTitulo())
                .imagenUrl(req.getImagenUrl())
                .enlaceDestino(req.getEnlaceDestino())
                .activo(req.getActivo())
                .build();
    }

    public BannerDto fromUpdate(BannerUpdateDto upd) {
        return BannerDto.builder()
                .id(upd.getId())
                .titulo(upd.getTitulo())
                .imagenUrl(upd.getImagenUrl())
                .enlaceDestino(upd.getEnlaceDestino())
                .activo(upd.getActivo())
                .build();
    }

    public BannerResponseDto toResponse(BannerDto dto) {
        return BannerResponseDto.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .imagenUrl(dto.getImagenUrl())
                .enlaceDestino(dto.getEnlaceDestino())
                .activo(dto.getActivo())
                .build();
    }
}
