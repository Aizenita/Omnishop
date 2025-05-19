package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;
import com.springwebappsb.omnishop.dto.request.DireccionEnvioRequestDto;
import com.springwebappsb.omnishop.dto.response.DireccionEnvioResponseDto;
import com.springwebappsb.omnishop.dto.update.DireccionEnvioUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class DireccionEnvioRestMapper {
    public DireccionEnvioDto fromRequest(DireccionEnvioRequestDto req) {
        return DireccionEnvioDto.builder()
                .usuarioId(req.getUsuarioId())
                .calle(req.getCalle())
                .ciudad(req.getCiudad())
                .cp(req.getCp())
                .pais(req.getPais())
                .predeterminada(req.getPredeterminada())
                .build();
    }

    public DireccionEnvioDto fromUpdate(Long id, DireccionEnvioUpdateDto upd) {
        return DireccionEnvioDto.builder()
                .id(id)
                .calle(upd.getCalle())
                .ciudad(upd.getCiudad())
                .cp(upd.getCp())
                .pais(upd.getPais())
                .predeterminada(upd.getPredeterminada())
                .build();
    }

    public DireccionEnvioResponseDto toResponse(DireccionEnvioDto dto) {
        return DireccionEnvioResponseDto.builder()
                .id(dto.getId())
                .usuarioId(dto.getUsuarioId())
                .calle(dto.getCalle())
                .ciudad(dto.getCiudad())
                .cp(dto.getCp())
                .pais(dto.getPais())
                .predeterminada(dto.getPredeterminada())
                .build();
    }
}

