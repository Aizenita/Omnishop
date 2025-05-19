package com.springwebappsb.omnishop.dto.update;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticiaUpdateDto {
    private Long id;
    private String titulo;
    private String contenido;
    private String imagenUrl;
    private LocalDateTime fechaPublicacion;
}
