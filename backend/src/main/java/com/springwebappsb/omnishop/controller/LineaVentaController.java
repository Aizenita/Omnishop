package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.request.LineaVentaRequestDto;
import com.springwebappsb.omnishop.dto.response.LineaVentaResponseDto;
import com.springwebappsb.omnishop.dto.update.LineaVentaUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.LineaVentaRestMapper;
import com.springwebappsb.omnishop.service.LineaVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lineas-venta")
@RequiredArgsConstructor
public class LineaVentaController {

    private final LineaVentaService service;
    private final LineaVentaRestMapper restMapper;

    @PostMapping
    public ResponseEntity<LineaVentaResponseDto> crear(@RequestBody LineaVentaRequestDto request) {
        return ResponseEntity.ok(restMapper.toResponse(
                service.crear(restMapper.fromRequest(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineaVentaResponseDto> actualizar(@PathVariable Long id,
                                                            @RequestBody LineaVentaUpdateDto request) {
        return ResponseEntity.ok(restMapper.toResponse(
                service.actualizar(restMapper.fromUpdate(id, request))));
    }

    @GetMapping
    public ResponseEntity<List<LineaVentaResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaVentaResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtener(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
