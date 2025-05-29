package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.ActualizarCantidadDto;
import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.repository.UsuarioRepository;
import com.springwebappsb.omnishop.service.CarritoService;
import com.springwebappsb.omnishop.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;
    private final VentaService ventaService;
    private final UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @GetMapping
    public ResponseEntity<VentaDto> obtenerCarrito(Authentication auth) {
        Long userId = getUsuarioIdDesdeAuth(auth);
        return ResponseEntity.ok(carritoService.obtenerCarritoActivo(userId));
    }

    @PostMapping("/agregar")
    public ResponseEntity<VentaDto> agregarProducto(@RequestParam Long productoId,
                                                    @RequestParam int cantidad,
                                                    Authentication auth) {
        Long userId = getUsuarioIdDesdeAuth(auth);
        return ResponseEntity.ok(carritoService.agregarProductoAlCarrito(userId, productoId, cantidad));
    }

    @DeleteMapping("/quitar")
    public ResponseEntity<VentaDto> quitarProducto(@RequestParam Long productoId, Authentication auth) {
        Long userId = getUsuarioIdDesdeAuth(auth);
        return ResponseEntity.ok(carritoService.quitarProductoDelCarrito(userId, productoId));
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<Void> vaciarCarrito(Authentication auth) {
        Long userId = getUsuarioIdDesdeAuth(auth);
        carritoService.vaciarCarrito(userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/cantidad")
    public ResponseEntity<?> actualizarCantidad(@RequestBody ActualizarCantidadDto dto, Authentication auth) {
        Long usuarioId = getUsuarioIdDesdeAuth(auth);
        ventaService.actualizarCantidadEnCarrito(usuarioId, dto.getProductoId(), dto.getCantidad());
        return ResponseEntity.ok().build();
    }

    private Long getUsuarioIdDesdeAuth(Authentication auth) {
        return usuarioRepository.findByEmail(auth.getName())
                .map(Usuario::getId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}