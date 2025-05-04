package com.springwebappsb.omnishop.dto.request;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoRequestDto {

    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String imagen;
    private Long categoriaId;


}
