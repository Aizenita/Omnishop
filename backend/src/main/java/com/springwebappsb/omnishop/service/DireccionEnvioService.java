package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;

import java.util.List;

public interface DireccionEnvioService {
    DireccionEnvioDto crear(DireccionEnvioDto dto);
    DireccionEnvioDto actualizar(DireccionEnvioDto dto);
    void eliminar(Long id);
    DireccionEnvioDto obtener(Long id);
    List<DireccionEnvioDto> listar();
}
