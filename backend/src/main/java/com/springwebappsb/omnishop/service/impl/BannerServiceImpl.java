package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.BannerDto;
import com.springwebappsb.omnishop.entity.Banner;
import com.springwebappsb.omnishop.mapper.internal.BannerMapper;
import com.springwebappsb.omnishop.repository.BannerRepository;
import com.springwebappsb.omnishop.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository repository;
    private final BannerMapper mapper;

    @Override
    public BannerDto crear(BannerDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public BannerDto actualizar(BannerDto dto) {
        Banner existente = repository.findById(dto.getId()).orElseThrow();
        existente.setTitulo(dto.getTitulo());
        existente.setImagenUrl(dto.getImagenUrl());
        existente.setEnlaceDestino(dto.getEnlaceDestino());
        existente.setActivo(dto.getActivo());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BannerDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<BannerDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
