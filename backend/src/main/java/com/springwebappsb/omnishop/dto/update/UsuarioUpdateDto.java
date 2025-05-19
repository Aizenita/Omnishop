package com.springwebappsb.omnishop.dto.update;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateDto {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private Boolean activo;
}
