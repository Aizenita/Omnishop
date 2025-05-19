package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.UsuarioDto;
import com.springwebappsb.omnishop.dto.request.UsuarioRequestDto;
import com.springwebappsb.omnishop.dto.response.UsuarioResponseDto;
import com.springwebappsb.omnishop.dto.update.UsuarioUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRestMapper {
    public UsuarioDto fromRequest(UsuarioRequestDto req) {
        return UsuarioDto.builder()
                .nombre(req.getNombre())
                .email(req.getEmail())
                .contrasena(req.getContrasena())
                .rol(req.getRol())
                .activo(true)
                .build();
    }

    public UsuarioDto fromUpdate(UsuarioUpdateDto upd) {
        return UsuarioDto.builder()
                .id(upd.getId())
                .nombre(upd.getNombre())
                .email(upd.getEmail())
                .rol(upd.getRol())
                .activo(upd.getActivo())
                .build();
    }

    public UsuarioResponseDto toResponse(UsuarioDto dto) {
        return UsuarioResponseDto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .rol(dto.getRol())
                .activo(dto.getActivo())
                .build();
    }
}
