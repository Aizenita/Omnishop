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
public class CategoriaDto {

    private Long id;
    private String nombre;


}
