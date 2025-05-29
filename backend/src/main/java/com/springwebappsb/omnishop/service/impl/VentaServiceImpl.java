package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.entity.LineaVenta;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.entity.Venta;
import com.springwebappsb.omnishop.enums.EstadoVenta;
import com.springwebappsb.omnishop.mapper.internal.VentaMapper;
import com.springwebappsb.omnishop.repository.VentaRepository;
import com.springwebappsb.omnishop.service.VentaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repository;
    private final VentaMapper mapper;

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
}
