package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.VentaDto;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.entity.Venta;
import com.springwebappsb.omnishop.mapper.internal.VentaMapper;
import com.springwebappsb.omnishop.repository.VentaRepository;
import com.springwebappsb.omnishop.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repository;
    private final VentaMapper mapper;

    @Override
    public VentaDto crear(VentaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public VentaDto actualizar(VentaDto dto) {
        Venta existente = repository.findById(dto.getId()).orElseThrow();
        existente.setTotal(dto.getTotal());
        Usuario u = new Usuario();
        u.setId(dto.getUsuarioId());
        existente.setUsuario(u);
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public VentaDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<VentaDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
