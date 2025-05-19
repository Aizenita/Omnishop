package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.request.NoticiaRequestDto;
import com.springwebappsb.omnishop.dto.response.NoticiaResponseDto;
import com.springwebappsb.omnishop.dto.update.NoticiaUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.NoticiaRestMapper;
import com.springwebappsb.omnishop.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/noticias")
@RequiredArgsConstructor
public class NoticiaController {
    private final NoticiaService service;
    private final NoticiaRestMapper restMapper;

    @PostMapping
    public ResponseEntity<NoticiaResponseDto> crear(@RequestBody NoticiaRequestDto request) {
        return ResponseEntity.ok(restMapper.toResponse(service.crear(restMapper.fromRequest(request))));
    }

    @PutMapping
    public ResponseEntity<NoticiaResponseDto> actualizar(@RequestBody NoticiaUpdateDto request) {
        return ResponseEntity.ok(restMapper.toResponse(service.actualizar(restMapper.fromUpdate(request))));
    }

    @GetMapping
    public ResponseEntity<List<NoticiaResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtener(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
