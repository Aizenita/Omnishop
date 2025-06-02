package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.dto.request.ItemPedidoDto;
import com.springwebappsb.omnishop.dto.request.PagoRequestDto;
import com.springwebappsb.omnishop.entity.*;
import com.springwebappsb.omnishop.enums.EstadoVenta;
import com.springwebappsb.omnishop.mapper.internal.VentaMapper;
import com.springwebappsb.omnishop.repository.*;
import com.springwebappsb.omnishop.service.VentaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repository;
    private final VentaMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final LineaVentaRepository lineaVentaRepository;
    private final DireccionEnvioRepository direccionEnvioRepository;

    @Override
    public VentaDto crear(VentaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public VentaDto actualizar(VentaDto dto) {
        Venta existente = repository.findById(dto.getId()).orElseThrow();
        existente.setTotal(dto.getTotal());
        Usuario u = new Usuario();
        u.setId(dto.getUsuarioId());
        existente.setUsuario(u);
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public VentaDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<VentaDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
    @Override
    @Transactional
    public void actualizarCantidadEnCarrito(Long usuarioId, Long productoId, int cantidad) {
        Venta carrito = obtenerOCrearCarritoActivo(usuarioId);
        LineaVenta linea = carrito.getLineasVenta().stream()
                .filter(l -> l.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Producto no está en el carrito"));

        if (cantidad <= 0) {
            carrito.getLineasVenta().remove(linea); // eliminar si cantidad <= 0
        } else {
            linea.setCantidad(cantidad);
        }

        repository.save(carrito);
    }

    @Override
    @Transactional
    public Venta crearVentaPendiente(PagoRequestDto dto, Authentication auth) {
        Usuario usuario = usuarioRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Venta venta = Venta.builder()
                .usuario(usuario)
                .estadoVenta(EstadoVenta.PENDIENTE_PAGO)
                .fechaCreacion(Instant.now())
                .total(BigDecimal.ZERO) // temporal
                .build();
        repository.save(venta);

        BigDecimal subtotal = BigDecimal.ZERO;

        for (ItemPedidoDto item : dto.getItems()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductoId()));

            LineaVenta linea = LineaVenta.builder()
                    .venta(venta)
                    .producto(producto)
                    .cantidad(item.getCantidad())
                    .precioUnitario(producto.getPrecio())
                    .build();

            subtotal = subtotal.add(producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
            lineaVentaRepository.save(linea);
        }

        BigDecimal gastosEnvio = new BigDecimal("5.00");
        BigDecimal impuestos = subtotal.multiply(new BigDecimal("0.10")); // 10%

        BigDecimal total = subtotal.add(gastosEnvio).add(impuestos);
        venta.setTotal(total);
        System.out.println("VENTA PERSISTIDA: " + venta.getId() + " Total: " + venta.getTotal());
        return repository.save(venta);
    }

    public void marcarVentaComoPagada(Long ventaId) {
        Venta venta = repository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setEstadoVenta(EstadoVenta.COMPLETADA);
        repository.save(venta);
        System.out.println("🟢 Venta marcada como pagada: " + ventaId);
    }



    private Venta obtenerOCrearCarritoActivo(Long usuarioId) {
        return repository.findByUsuarioIdAndEstadoVenta(usuarioId, EstadoVenta.CARRITO_ACTIVO)
                .orElseGet(() -> {
                    Venta nueva = new Venta();
                    nueva.setUsuario(repository.findById(usuarioId).orElseThrow().getUsuario());
                    nueva.setEstadoVenta(EstadoVenta.CARRITO_ACTIVO);
                    nueva.setFechaCreacion(Instant.now());
                    return repository.save(nueva);
                });
    }

    public Venta crearVentaPagadaSimulada(PagoRequestDto dto, Authentication auth) {
        // 1. Lógica muy parecida a crearVentaPendiente
        Venta venta = new Venta();
        Usuario usuario = usuarioRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        venta.setUsuario(usuario);
        venta.setEstadoVenta(EstadoVenta.valueOf("PAGO_SIMULADO"));
        venta.setFecha(Instant.now());
        venta.setTotal(BigDecimal.valueOf(dto.getTotal()));

        DireccionEnvio direccion = direccionEnvioRepository.findById(dto.getDireccionEnvioId())
                .orElseThrow(() -> new RuntimeException("Dirección de envío no encontrada"));


        repository.save(venta);

        // Añadir líneas de venta
        for (ItemPedidoDto item : dto.getItems()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            LineaVenta linea = new LineaVenta();
            linea.setVenta(venta);
            linea.setProducto(producto);
            linea.setCantidad(item.getCantidad());
            linea.setPrecioUnitario(producto.getPrecio());
            lineaVentaRepository.save(linea);
        }

        return venta;
    }

}
