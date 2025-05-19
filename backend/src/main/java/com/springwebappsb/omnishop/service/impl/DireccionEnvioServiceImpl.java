package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;
import com.springwebappsb.omnishop.entity.DireccionEnvio;
import com.springwebappsb.omnishop.mapper.internal.DireccionEnvioMapper;
import com.springwebappsb.omnishop.repository.DireccionEnvioRepository;
import com.springwebappsb.omnishop.service.DireccionEnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionEnvioServiceImpl implements DireccionEnvioService {
    private final DireccionEnvioRepository repository;
    private final DireccionEnvioMapper mapper;

    @Override
    public DireccionEnvioDto crear(DireccionEnvioDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public DireccionEnvioDto actualizar(DireccionEnvioDto dto) {
        DireccionEnvio existente = repository.findById(dto.getId()).orElseThrow();
        existente.setCalle(dto.getCalle());
        existente.setCiudad(dto.getCiudad());
        existente.setCp(dto.getCp());
        existente.setPais(dto.getPais());
        existente.setPredeterminada(dto.getPredeterminada());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DireccionEnvioDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<DireccionEnvioDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}