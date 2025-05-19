package com.springwebappsb.omnishop.mapper.rest;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import com.springwebappsb.omnishop.dto.request.LineaVentaRequestDto;
import com.springwebappsb.omnishop.dto.response.LineaVentaResponseDto;
import com.springwebappsb.omnishop.dto.update.LineaVentaUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class LineaVentaRestMapper {
    public LineaVentaDto fromRequest(LineaVentaRequestDto req) {
        return LineaVentaDto.builder()
                .ventaId(req.getVentaId())
                .productoId(req.getProductoId())
                .cantidad(req.getCantidad())
                .precioUnitario(req.getPrecioUnitario())
                .build();
    }

    public LineaVentaDto fromUpdate(Long id, LineaVentaUpdateDto upd) {
        return LineaVentaDto.builder()
                .id(id)
                .cantidad(upd.getCantidad())
                .precioUnitario(upd.getPrecioUnitario())
                .build();
    }

    public LineaVentaResponseDto toResponse(LineaVentaDto dto) {
        return LineaVentaResponseDto.builder()
                .id(dto.getId())
                .ventaId(dto.getVentaId())
                .productoId(dto.getProductoId())
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .build();
    }
}
