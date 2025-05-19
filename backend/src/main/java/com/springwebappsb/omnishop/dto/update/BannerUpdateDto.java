package com.springwebappsb.omnishop.dto.update;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerUpdateDto {
    private Long id;
    private String titulo;
    private String imagenUrl;
    private String enlaceDestino;
    private Boolean activo;
}
