package com.springwebappsb.omnishop.controller;


import com.springwebappsb.omnishop.dto.ProductoDto;
import com.springwebappsb.omnishop.dto.request.ProductoRequestDto;
import com.springwebappsb.omnishop.dto.response.ProductoResponseDto;
import com.springwebappsb.omnishop.dto.update.ProductoUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.ProductoRestMapper;
import com.springwebappsb.omnishop.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRestMapper productoRestMapper;

    @PostMapping
    public ResponseEntity<ProductoResponseDto> crear(@RequestBody ProductoRequestDto request) {
        ProductoDto dto= productoRestMapper.fromRequest(request);
        ProductoDto creado= productoService.crearProducto(dto, request.getCategoriaId());
        return ResponseEntity.ok(productoRestMapper.toResponse(creado));
    }

    @PutMapping
    public ResponseEntity<ProductoResponseDto> actualizar(@RequestBody ProductoUpdateDto request) {
        ProductoDto dto= productoRestMapper.fromUpdate(request);
        ProductoDto actualizado= productoService.actualizarProducto(dto, request.getCategoriaId());
        return ResponseEntity.ok(productoRestMapper.toResponse(actualizado));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> listar() {
        return ResponseEntity.ok(productoService.listarProductos()
                .stream()
                .map(productoRestMapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(productoRestMapper.toResponse(productoService.obtenerProducto(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
