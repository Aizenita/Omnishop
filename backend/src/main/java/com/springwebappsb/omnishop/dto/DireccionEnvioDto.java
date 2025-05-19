package com.springwebappsb.omnishop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionEnvioDto {
    private Long id;
    private Long usuarioId;
    private String calle;
    private String ciudad;
    private String cp;
    private String pais;
    private Boolean predeterminada;
}
