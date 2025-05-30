package com.springwebappsb.omnishop.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeResponse {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}
