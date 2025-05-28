package com.springwebappsb.omnishop.dto;


import com.springwebappsb.omnishop.entity.Categoria;
import com.springwebappsb.omnishop.entity.LineaVenta;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


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
    private Boolean destacado;
    private Boolean visible;
    private Set<LineaVenta> lineaVenta;
}
