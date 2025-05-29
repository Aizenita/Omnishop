package com.springwebappsb.omnishop.dto.request;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaRequestDto {
    private BigDecimal total;
    private Long usuarioId;
    private Set<LineaVentaDto> lineasVenta;
    private String estadoVenta;
}
