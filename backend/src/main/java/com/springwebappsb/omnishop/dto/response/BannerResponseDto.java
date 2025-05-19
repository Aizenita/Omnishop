package com.springwebappsb.omnishop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerResponseDto {
    private Long id;
    private String titulo;
    private String imagenUrl;
    private String enlaceDestino;
    private Boolean activo;
}
