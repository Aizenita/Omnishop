package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.LineaVentaDto;
import com.springwebappsb.omnishop.entity.LineaVenta;
import com.springwebappsb.omnishop.mapper.internal.LineaVentaMapper;
import com.springwebappsb.omnishop.repository.LineaVentaRepository;
import com.springwebappsb.omnishop.service.LineaVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineaVentaServiceImpl implements LineaVentaService {

    private final LineaVentaRepository repository;
    private final LineaVentaMapper mapper;

    @Override
    public LineaVentaDto crear(LineaVentaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public LineaVentaDto actualizar(LineaVentaDto dto) {
        LineaVenta existente = repository.findById(dto.getId()).orElseThrow();
        existente.setCantidad(dto.getCantidad());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LineaVentaDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<LineaVentaDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}

