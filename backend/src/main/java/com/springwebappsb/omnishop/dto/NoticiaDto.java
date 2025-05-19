package com.springwebappsb.omnishop.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticiaDto {
    private Long id;
    private String titulo;
    private String contenido;
    private String imagenUrl;
    private LocalDateTime fechaPublicacion;
}
