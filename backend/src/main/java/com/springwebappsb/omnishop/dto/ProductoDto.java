package com.springwebappsb.omnishop.dto;


import com.springwebappsb.omnishop.entity.Categoria;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDto {

    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String imagen;
    private Categoria categoria;
}
