package com.springwebappsb.omnishop.service.impl;


import com.springwebappsb.omnishop.dto.CategoriaDto;
import com.springwebappsb.omnishop.entity.Categoria;
import com.springwebappsb.omnishop.mapper.internal.CategoriaMapper;
import com.springwebappsb.omnishop.repository.CategoriaRepository;
import com.springwebappsb.omnishop.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Override
    public CategoriaDto crear(CategoriaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public CategoriaDto actualizar(CategoriaDto dto) {
        Categoria existente = repository.findById(dto.getId()).orElseThrow();
        existente.setNombre(dto.getNombre());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CategoriaDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<CategoriaDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
