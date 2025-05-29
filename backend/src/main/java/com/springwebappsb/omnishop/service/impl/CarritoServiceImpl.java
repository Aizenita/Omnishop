package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.entity.LineaVenta;
import com.springwebappsb.omnishop.entity.Producto;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.entity.Venta;
import com.springwebappsb.omnishop.enums.EstadoVenta;
import com.springwebappsb.omnishop.repository.LineaVentaRepository;
import com.springwebappsb.omnishop.repository.ProductoRepository;
import com.springwebappsb.omnishop.repository.UsuarioRepository;
import com.springwebappsb.omnishop.repository.VentaRepository;
import com.springwebappsb.omnishop.service.CarritoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final LineaVentaRepository lineaVentaRepository;

    @Override
    public VentaDto obtenerCarritoActivo(Long usuarioId) {
        Venta venta = ventaRepository.findByUsuarioIdAndEstadoVenta(usuarioId, EstadoVenta.CARRITO_ACTIVO)
                .orElseGet(() -> crearNuevoCarrito(usuarioId));
        return mapearVenta(venta);
    }

    @Override
    public VentaDto agregarProductoAlCarrito(Long usuarioId, Long productoId, int cantidad) {
        Venta carrito = ventaRepository.findByUsuarioIdAndEstadoVenta(usuarioId, EstadoVenta.CARRITO_ACTIVO)
                .orElseGet(() -> crearNuevoCarrito(usuarioId));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Optional<LineaVenta> existente = carrito.getLineasVenta().stream()
                .filter(lv -> lv.getProducto().getId().equals(productoId))
                .findFirst();

        if (existente.isPresent()) {
            existente.get().setCantidad(existente.get().getCantidad() + cantidad);
        } else {
            LineaVenta linea = LineaVenta.builder()
                    .producto(producto)
                    .venta(carrito)
                    .cantidad(cantidad)
                    .precioUnitario(producto.getPrecio())
                    .build();
            carrito.getLineasVenta().add(linea);
        }

        return mapearVenta(ventaRepository.save(carrito));
    }

    @Override
    public VentaDto quitarProductoDelCarrito(Long usuarioId, Long productoId) {
        Venta carrito = ventaRepository.findByUsuarioIdAndEstadoVenta(usuarioId, EstadoVenta.CARRITO_ACTIVO)
                .orElseThrow(() -> new EntityNotFoundException("No hay carrito activo"));

        carrito.getLineasVenta().removeIf(lv -> lv.getProducto().getId().equals(productoId));

        return mapearVenta(ventaRepository.save(carrito));
    }

    @Override
    public void vaciarCarrito(Long usuarioId) {
        Venta carrito = ventaRepository.findByUsuarioIdAndEstadoVenta(usuarioId, EstadoVenta.CARRITO_ACTIVO)
                .orElseThrow(() -> new EntityNotFoundException("No hay carrito activo"));
        carrito.getLineasVenta().clear();
        ventaRepository.save(carrito);
    }

    private Venta crearNuevoCarrito(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Venta venta = Venta.builder()
                .usuario(usuario)
                .estadoVenta(EstadoVenta.CARRITO_ACTIVO)
                .fechaCreacion(Instant.now())
                .fechaModificacion(Instant.now())
                .build();
        return ventaRepository.save(venta);
    }

    private VentaDto mapearVenta(Venta venta) {
        BigDecimal total = venta.getLineasVenta().stream()
                .map(lv -> lv.getPrecioUnitario().multiply(BigDecimal.valueOf(lv.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Set<LineaVentaDto> lineas = venta.getLineasVenta().stream().map(lv ->
                LineaVentaDto.builder()
                        .id(lv.getId())
                        .productoId(lv.getProducto().getId())
                        .nombreProducto(lv.getProducto().getNombre())
                        .precioUnitario(lv.getPrecioUnitario())
                        .cantidad(lv.getCantidad())
                        .subtotal(lv.getSubtotal())
                        .build()
        ).collect(Collectors.toSet());

        return VentaDto.builder()
                .id(venta.getId())
                .usuarioId(venta.getUsuario().getId())
                .lineasVenta(lineas)
                .total(total)
                .estadoVenta(venta.getEstadoVenta().name())
                .build();
    }
}

