package com.springwebappsb.omnishop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;
    private Boolean activo;
}