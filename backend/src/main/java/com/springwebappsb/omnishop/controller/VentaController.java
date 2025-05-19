package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.dto.request.VentaRequestDto;
import com.springwebappsb.omnishop.dto.response.VentaResponseDto;
import com.springwebappsb.omnishop.dto.update.VentaUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.VentaRestMapper;
import com.springwebappsb.omnishop.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService service;
    private final VentaRestMapper restMapper;

    @PostMapping
    public ResponseEntity<VentaResponseDto> crear(@RequestBody VentaRequestDto request) {
        VentaDto dto = restMapper.fromRequest(request);
        return ResponseEntity.ok(restMapper.toResponse(service.crear(dto)));
    }

    @PutMapping
    public ResponseEntity<VentaResponseDto> actualizar(@RequestBody VentaUpdateDto request) {
        VentaDto dto = restMapper.fromUpdate(request);
        return ResponseEntity.ok(restMapper.toResponse(service.actualizar(dto)));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtener(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
