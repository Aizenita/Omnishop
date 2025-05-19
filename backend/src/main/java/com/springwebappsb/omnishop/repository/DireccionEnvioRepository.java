package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.DireccionEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionEnvioRepository extends JpaRepository<DireccionEnvio, Long> {}

