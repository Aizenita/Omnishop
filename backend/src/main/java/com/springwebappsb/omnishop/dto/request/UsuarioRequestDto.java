package com.springwebappsb.omnishop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDto {
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;
}
