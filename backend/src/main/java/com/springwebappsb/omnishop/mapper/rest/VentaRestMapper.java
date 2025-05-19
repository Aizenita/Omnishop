package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.dto.request.VentaRequestDto;
import com.springwebappsb.omnishop.dto.response.VentaResponseDto;
import com.springwebappsb.omnishop.dto.update.VentaUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class VentaRestMapper {
    public VentaDto fromRequest(VentaRequestDto req) {
        return VentaDto.builder()
                .total(req.getTotal())
                .usuarioId(req.getUsuarioId())
                .build();
    }

    public VentaDto fromUpdate(VentaUpdateDto upd) {
        return VentaDto.builder()
                .id(upd.getId())
                .total(upd.getTotal())
                .usuarioId(upd.getUsuarioId())
                .build();
    }

    public VentaResponseDto toResponse(VentaDto dto) {
        return VentaResponseDto.builder()
                .id(dto.getId())
                .total(dto.getTotal())
                .fecha(dto.getFecha())
                .usuarioId(dto.getUsuarioId())
                .build();
    }
}
