package com.springwebappsb.omnishop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionEnvioRequestDto {
    private String calle;
    private String ciudad;
    private String cp;
    private String pais;
    private Boolean predeterminada;

    public boolean isPredeterminada() {
        return predeterminada;
    }
}

