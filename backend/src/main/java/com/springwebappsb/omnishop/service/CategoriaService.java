package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.CategoriaDto;

import java.util.List;

public interface CategoriaService {

    CategoriaDto crear(CategoriaDto dto);
    CategoriaDto actualizar(CategoriaDto dto);
    void eliminar(Long id);
    CategoriaDto obtener(Long id);
    List<CategoriaDto> listar();

}
