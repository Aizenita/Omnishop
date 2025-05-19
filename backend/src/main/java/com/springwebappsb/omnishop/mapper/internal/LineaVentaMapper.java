package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import com.springwebappsb.omnishop.entity.LineaVenta;
import com.springwebappsb.omnishop.entity.Producto;
import com.springwebappsb.omnishop.entity.Venta;
import org.springframework.stereotype.Component;

@Component
public class LineaVentaMapper {
    public LineaVentaDto toDto(LineaVenta entity) {
        return LineaVentaDto.builder()
                .id(entity.getId())
                .ventaId(entity.getVenta().getId())
                .productoId(entity.getProducto().getId())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .build();
    }

    public LineaVenta toEntity(LineaVentaDto dto) {
        Venta venta = new Venta();
        venta.setId(dto.getVentaId());
        Producto producto = new Producto();
        producto.setId(dto.getProductoId());
        return LineaVenta.builder()
                .id(dto.getId())
                .venta(venta)
                .producto(producto)
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .build();
    }
}
