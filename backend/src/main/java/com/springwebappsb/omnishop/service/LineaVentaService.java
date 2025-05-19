package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.LineaVentaDto;

import java.util.List;

public interface LineaVentaService {
    LineaVentaDto crear(LineaVentaDto dto);
    LineaVentaDto actualizar(LineaVentaDto dto);
    void eliminar(Long id);
    LineaVentaDto obtener(Long id);
    List<LineaVentaDto> listar();
}
