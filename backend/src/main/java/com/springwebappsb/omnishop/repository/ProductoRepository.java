package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {


    List<Producto> findByNombre(String nombre);
    List<Producto> findByNombreContainingIgnoreCase(String texto);
    List<Producto> findByPrecioBetween(BigDecimal min, BigDecimal max);
    List<Producto> findByPrecioGreaterThan(BigDecimal precio);
    List<Producto> findByPrecioLessThan(BigDecimal precio);
    List<Producto> findByCategoriaId(Long categoriaId);
    List<Producto> findByNombreAndCategoriaId(String nombre, Long categoriaId);




}
