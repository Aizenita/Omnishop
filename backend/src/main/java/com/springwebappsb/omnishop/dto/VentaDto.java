package com.springwebappsb.omnishop.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDto {

    private Long id;
    private BigDecimal total;
    private Instant fecha;
    private Long usuarioId;
}
