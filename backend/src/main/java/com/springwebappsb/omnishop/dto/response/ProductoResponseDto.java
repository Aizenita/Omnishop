package com.springwebappsb.omnishop.dto.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoResponseDto {

    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String imagen;
    private Long categoriaId;

}
