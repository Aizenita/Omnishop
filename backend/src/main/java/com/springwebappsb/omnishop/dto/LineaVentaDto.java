package com.springwebappsb.omnishop.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaVentaDto {
    private Long id;
    private Long ventaId;
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
