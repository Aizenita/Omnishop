package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;
import com.springwebappsb.omnishop.dto.request.DireccionEnvioRequestDto;
import com.springwebappsb.omnishop.dto.response.DireccionEnvioResponseDto;
import com.springwebappsb.omnishop.dto.update.DireccionEnvioUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.DireccionEnvioRestMapper;
import com.springwebappsb.omnishop.service.DireccionEnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/direcciones")
@RequiredArgsConstructor
public class DireccionEnvioController {

    private final DireccionEnvioService service;
    private final DireccionEnvioRestMapper restMapper;

    @PostMapping
    public ResponseEntity<DireccionEnvioResponseDto> crear(@RequestBody DireccionEnvioRequestDto request) {
        return ResponseEntity.ok(restMapper.toResponse(service.crear(restMapper.fromRequest(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionEnvioResponseDto> actualizar(@PathVariable Long id,
                                                                @RequestBody DireccionEnvioUpdateDto request) {
        return ResponseEntity.ok(restMapper.toResponse(service.actualizar(restMapper.fromUpdate(id, request))));
    }

    @GetMapping
    public ResponseEntity<List<DireccionEnvioResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionEnvioResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtener(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<DireccionEnvioResponseDto> crear(@RequestBody @Valid DireccionEnvioRequestDto dto,
                                                           Authentication auth) {
        DireccionEnvioDto creada = service.crear(dto, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toResponse(creada));
    }
    @PutMapping("/{id}/predeterminada")
    public ResponseEntity<Void> marcarComoPredeterminada(@PathVariable Long id, Authentication auth) {
        service.marcarComoPredeterminada(id, auth);
        return ResponseEntity.noContent().build();
    }

}

