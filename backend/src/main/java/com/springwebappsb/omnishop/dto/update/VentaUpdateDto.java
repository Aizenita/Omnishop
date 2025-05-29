package com.springwebappsb.omnishop.dto.update;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaUpdateDto {
    private Long id;
    private BigDecimal total;
    private Long usuarioId;
    private Set<LineaVentaDto> lineasVenta;
    private String estadoVenta;
}