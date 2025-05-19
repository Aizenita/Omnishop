package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.BannerDto;

import java.util.List;

public interface BannerService {
    BannerDto crear(BannerDto dto);
    BannerDto actualizar(BannerDto dto);
    void eliminar(Long id);
    BannerDto obtener(Long id);
    List<BannerDto> listar();
}
