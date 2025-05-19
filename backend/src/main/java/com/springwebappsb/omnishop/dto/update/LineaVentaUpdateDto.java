package com.springwebappsb.omnishop.dto.update;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaVentaUpdateDto {
    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
