package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.CategoriaDto;
import com.springwebappsb.omnishop.dto.request.CategoriaRequestDto;
import com.springwebappsb.omnishop.dto.response.CategoriaResponseDto;
import com.springwebappsb.omnishop.dto.update.CategoriaUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.CategoriaRestMapper;
import com.springwebappsb.omnishop.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;
    private final CategoriaRestMapper restMapper;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> crear(@RequestBody CategoriaRequestDto request) {
        CategoriaDto dto = restMapper.fromRequest(request);
        return ResponseEntity.ok(restMapper.toResponse(service.crear(dto)));
    }

    @PutMapping
    public ResponseEntity<CategoriaResponseDto> actualizar(@RequestBody CategoriaUpdateDto request) {
        CategoriaDto dto = restMapper.fromUpdate(request);
        return ResponseEntity.ok(restMapper.toResponse(service.actualizar(dto)));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtener(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
