package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {}

