package com.springwebappsb.omnishop.dto.update;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaUpdateDto {
    private Long id;
    private BigDecimal total;
    private Long usuarioId;
}