package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;
import com.springwebappsb.omnishop.dto.request.DireccionEnvioRequestDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DireccionEnvioService {
    DireccionEnvioDto crear(DireccionEnvioDto dto);
    DireccionEnvioDto actualizar(DireccionEnvioDto dto);
    void eliminar(Long id);
    DireccionEnvioDto obtener(Long id);
    List<DireccionEnvioDto> listar();
    DireccionEnvioDto crear(DireccionEnvioRequestDto dto, Authentication auth);
    List<DireccionEnvioDto> listarPorUsuario(Authentication auth);

    void marcarComoPredeterminada(Long id, Authentication auth);
}
