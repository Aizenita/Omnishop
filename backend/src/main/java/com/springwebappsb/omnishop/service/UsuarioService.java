package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto crearUsuario(UsuarioDto dto);
    UsuarioDto actualizarUsuario(UsuarioDto dto);
    void eliminarUsuario(Long id);
    UsuarioDto obtenerUsuario(Long id);
    List<UsuarioDto> listarUsuarios();
}
