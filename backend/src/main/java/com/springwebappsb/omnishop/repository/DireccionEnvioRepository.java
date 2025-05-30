package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.DireccionEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionEnvioRepository extends JpaRepository<DireccionEnvio, Long> {
    List<DireccionEnvio> findByUsuarioId(Long usuarioId);
    @Modifying
    @Query("UPDATE DireccionEnvio d SET d.predeterminada = false WHERE d.usuario.id = :usuarioId")
    void desmarcarDireccionesPredeterminadas(Long usuarioId);
}

