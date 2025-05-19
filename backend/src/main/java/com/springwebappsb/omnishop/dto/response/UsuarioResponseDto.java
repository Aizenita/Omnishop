package com.springwebappsb.omnishop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private Boolean activo;
}
