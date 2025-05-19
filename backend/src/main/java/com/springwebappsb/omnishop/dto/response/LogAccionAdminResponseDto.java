package com.springwebappsb.omnishop.dto.response;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAccionAdminResponseDto {
    private Long id;
    private Long adminId;
    private String accion;
    private Instant fecha;
}