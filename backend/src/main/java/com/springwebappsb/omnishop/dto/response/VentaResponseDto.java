package com.springwebappsb.omnishop.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

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
}
