package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.UsuarioDto;
import com.springwebappsb.omnishop.dto.request.UsuarioRequestDto;
import com.springwebappsb.omnishop.dto.response.UsuarioResponseDto;
import com.springwebappsb.omnishop.dto.update.UsuarioUpdateDto;
import com.springwebappsb.omnishop.mapper.rest.UsuarioRestMapper;
import com.springwebappsb.omnishop.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioRestMapper restMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> crear(@RequestBody UsuarioRequestDto request) {
        UsuarioDto dto = restMapper.fromRequest(request);
        return ResponseEntity.ok(restMapper.toResponse(service.crearUsuario(dto)));
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> actualizar(@RequestBody UsuarioUpdateDto request) {
        UsuarioDto dto = restMapper.fromUpdate(request);
        return ResponseEntity.ok(restMapper.toResponse(service.actualizarUsuario(dto)));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        return ResponseEntity.ok(service.listarUsuarios().stream().map(restMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toResponse(service.obtenerUsuario(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
