package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.request.BannerRequestDto;
import com.springwebappsb.omnishop.dto.response.BannerResponseDto;
import com.springwebappsb.omnishop.dto.update.BannerUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.BannerRestMapper;
import com.springwebappsb.omnishop.service.BannerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/banners")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService service;
    private final BannerRestMapper restMapper;

    @PostMapping
    public ResponseEntity<BannerResponseDto> crear(@RequestBody BannerRequestDto request) {
        return ResponseEntity.ok(restMapper.toResponse(service.crear(restMapper.fromRequest(request))));
    }

    @PutMapping
    public ResponseEntity<BannerResponseDto> actualizar(@RequestBody BannerUpdateDto request) {
        return ResponseEntity.ok(restMapper.toResponse(service.actualizar(restMapper.fromUpdate(request))));
    }

    @GetMapping
    public ResponseEntity<List<BannerResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BannerResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtener(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

