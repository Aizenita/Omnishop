package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.entity.Venta;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class VentaMapper {
    public VentaDto toDto(Venta entity) {
        return VentaDto.builder()
                .id(entity.getId())
                .total(entity.getTotal())
                .fecha(entity.getFecha())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .build();
    }

    public Venta toEntity(VentaDto dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        return Venta.builder()
                .id(dto.getId())
                .total(dto.getTotal())
                .fecha(Instant.now())
                .usuario(usuario)
                .build();
    }
}
