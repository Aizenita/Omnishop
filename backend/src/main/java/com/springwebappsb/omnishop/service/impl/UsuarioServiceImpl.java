package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.UsuarioDto;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.enums.Rol;
import com.springwebappsb.omnishop.mapper.internal.UsuarioMapper;
import com.springwebappsb.omnishop.repository.UsuarioRepository;
import com.springwebappsb.omnishop.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public UsuarioDto crearUsuario(UsuarioDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public UsuarioDto actualizarUsuario(UsuarioDto dto) {
        Usuario existente = repository.findById(dto.getId()).orElseThrow();
        existente.setNombre(dto.getNombre());
        existente.setEmail(dto.getEmail());
        existente.setRol(Rol.valueOf(dto.getRol()));
        existente.setActivo(dto.getActivo());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioDto obtenerUsuario(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}

