package com.springwebappsb.omnishop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerRequestDto {
    private String titulo;
    private String imagenUrl;
    private String enlaceDestino;
    private Boolean activo;
}
