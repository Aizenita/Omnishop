package com.springwebappsb.omnishop.dto.update;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoUpdateDto {

    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String imagen;
    private Long categoriaId;
    private boolean destacado;
    private boolean visible;

}
