package com.springwebappsb.omnishop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionEnvioResponseDto {
    private Long id;
    private Long usuarioId;
    private String calle;
    private String ciudad;
    private String cp;
    private String pais;
    private Boolean predeterminada;
}
