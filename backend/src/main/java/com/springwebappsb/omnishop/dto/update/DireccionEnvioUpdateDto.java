package com.springwebappsb.omnishop.dto.update;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionEnvioUpdateDto {
    private Long id;
    private String calle;
    private String ciudad;
    private String cp;
    private String pais;
    private Boolean predeterminada;
}