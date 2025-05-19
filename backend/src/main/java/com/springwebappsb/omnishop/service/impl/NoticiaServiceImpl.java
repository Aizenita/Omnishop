package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.NoticiaDto;
import com.springwebappsb.omnishop.entity.Noticia;
import com.springwebappsb.omnishop.mapper.internal.NoticiaMapper;
import com.springwebappsb.omnishop.repository.NoticiaRepository;
import com.springwebappsb.omnishop.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {
    private final NoticiaRepository repository;
    private final NoticiaMapper mapper;

    @Override
    public NoticiaDto crear(NoticiaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public NoticiaDto actualizar(NoticiaDto dto) {
        Noticia existente = repository.findById(dto.getId()).orElseThrow();
        existente.setTitulo(dto.getTitulo());
        existente.setContenido(dto.getContenido());
        existente.setImagenUrl(dto.getImagenUrl());
        existente.setFechaPublicacion(dto.getFechaPublicacion());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public NoticiaDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<NoticiaDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
