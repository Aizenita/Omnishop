package com.springwebappsb.omnishop.dto.request;

import lombok.Data;
import lombok.Getter;
import java.util.List;


@Getter
@Data
public class PagoRequestDto {
    private Long direccionEnvioId;
    private List<ItemPedidoDto> items;
    private Double total;
}
