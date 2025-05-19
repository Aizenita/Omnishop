package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.NoticiaDto;

import java.util.List;

public interface NoticiaService {
    NoticiaDto crear(NoticiaDto dto);
    NoticiaDto actualizar(NoticiaDto dto);
    void eliminar(Long id);
    NoticiaDto obtener(Long id);
    List<NoticiaDto> listar();
}
