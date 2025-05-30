package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;
import com.springwebappsb.omnishop.dto.request.DireccionEnvioRequestDto;
import com.springwebappsb.omnishop.entity.DireccionEnvio;
import com.springwebappsb.omnishop.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DireccionEnvioMapper {
    public DireccionEnvioDto toDto(DireccionEnvio entity) {
        return DireccionEnvioDto.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuario().getId())
                .calle(entity.getCalle())
                .ciudad(entity.getCiudad())
                .cp(entity.getCp())
                .pais(entity.getPais())
                .predeterminada(entity.getPredeterminada())
                .build();
    }

    public DireccionEnvio toEntity(DireccionEnvioDto dto) {
        Usuario u = new Usuario();
        u.setId(dto.getUsuarioId());
        return DireccionEnvio.builder()
                .id(dto.getId())
                .usuario(u)
                .calle(dto.getCalle())
                .ciudad(dto.getCiudad())
                .cp(dto.getCp())
                .pais(dto.getPais())
                .predeterminada(dto.getPredeterminada())
                .build();
    }
    public DireccionEnvio toEntity(DireccionEnvioRequestDto dto) {
        return DireccionEnvio.builder()
                .calle(dto.getCalle())
                .ciudad(dto.getCiudad())
                .cp(dto.getCp())
                .pais(dto.getPais())
                .predeterminada(dto.isPredeterminada())
                .build();
    }

}

