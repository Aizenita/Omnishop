package com.springwebappsb.omnishop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
    private Long id;
    private String titulo;
    private String imagenUrl;
    private String enlaceDestino;
    private Boolean activo;
}
