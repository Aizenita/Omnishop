package com.springwebappsb.omnishop.dto.response;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaResponseDto {
    private Long id;
    private BigDecimal total;
    private Instant fecha;
    private Long usuarioId;
    private Set<LineaVentaDto> lineasVenta;
    private String estadoVenta;
}
