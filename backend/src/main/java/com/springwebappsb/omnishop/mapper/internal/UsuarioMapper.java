package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.UsuarioDto;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.enums.Rol;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuario entity) {
        return UsuarioDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .contrasena(entity.getContraseña())
                .rol(String.valueOf(entity.getRol()))
                .activo(entity.getActivo())
                .build();
    }

    public Usuario toEntity(UsuarioDto dto) {
        return Usuario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .contraseña(dto.getContrasena())
                .rol(Rol.valueOf(dto.getRol()))
                .activo(dto.getActivo())
                .build();
    }


}
