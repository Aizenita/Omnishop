package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByRol(String rol);
    Optional<Usuario> findByActivo(boolean activo);

}
