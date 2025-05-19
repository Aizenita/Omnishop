package com.springwebappsb.omnishop.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaRequestDto {
    private BigDecimal total;
    private Long usuarioId;
}
