package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.DireccionEnvioDto;
import com.springwebappsb.omnishop.dto.request.DireccionEnvioRequestDto;
import com.springwebappsb.omnishop.entity.DireccionEnvio;
import com.springwebappsb.omnishop.entity.Usuario;
import com.springwebappsb.omnishop.mapper.internal.DireccionEnvioMapper;
import com.springwebappsb.omnishop.repository.DireccionEnvioRepository;
import com.springwebappsb.omnishop.repository.UsuarioRepository;
import com.springwebappsb.omnishop.service.DireccionEnvioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionEnvioServiceImpl implements DireccionEnvioService {
    private final DireccionEnvioRepository repository;
    private final DireccionEnvioMapper mapper;
    private final UsuarioRepository usuarioRepository;

    @Override
    public DireccionEnvioDto crear(DireccionEnvioDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public DireccionEnvioDto actualizar(DireccionEnvioDto dto) {
        DireccionEnvio existente = repository.findById(dto.getId()).orElseThrow();
        existente.setCalle(dto.getCalle());
        existente.setCiudad(dto.getCiudad());
        existente.setCp(dto.getCp());
        existente.setPais(dto.getPais());
        existente.setPredeterminada(dto.getPredeterminada());
        return mapper.toDto(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DireccionEnvioDto obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<DireccionEnvioDto> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
    private Long getUsuarioIdDesdeAuth(Authentication auth) {
        return usuarioRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"))
                .getId();
    }
    @Override
    public DireccionEnvioDto crear(DireccionEnvioRequestDto dto, Authentication auth) {
        Long usuarioId = getUsuarioIdDesdeAuth(auth);

        if (dto.isPredeterminada()) {
            repository.desmarcarDireccionesPredeterminadas(usuarioId);
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        DireccionEnvio direccion = mapper.toEntity(dto);
        direccion.setUsuario(usuario);

        return mapper.toDto(repository.save(direccion));
    }
    @Override
    public List<DireccionEnvioDto> listarPorUsuario(Authentication auth) {
        Long usuarioId = getUsuarioIdDesdeAuth(auth);
        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void marcarComoPredeterminada(Long id, Authentication auth) {
        Long usuarioId = getUsuarioIdDesdeAuth(auth);

        DireccionEnvio direccion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada"));

        if (!direccion.getUsuario().getId().equals(usuarioId)) {
            throw new AccessDeniedException("No tienes permiso para modificar esta dirección");
        }

        // Desmarcar otras
        repository.desmarcarDireccionesPredeterminadas(usuarioId);

        // Marcar esta como predeterminada
        direccion.setPredeterminada(true);
        repository.save(direccion);
    }
}