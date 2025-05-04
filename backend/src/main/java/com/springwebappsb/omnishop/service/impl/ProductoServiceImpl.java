package com.springwebappsb.omnishop.service.impl;

import com.springwebappsb.omnishop.dto.ProductoDto;
import com.springwebappsb.omnishop.entity.Categoria;
import com.springwebappsb.omnishop.entity.Producto;
import com.springwebappsb.omnishop.mapper.internal.ProductoMapper;
import com.springwebappsb.omnishop.repository.CategoriaRepository;
import com.springwebappsb.omnishop.repository.ProductoRepository;
import com.springwebappsb.omnishop.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;
    private CategoriaRepository categoriaRepository;
    private ProductoMapper productoMapper;


    @Override
    public ProductoDto crearProducto(ProductoDto dto, Long categoriaId) {
        Categoria categoria= categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        dto.setCategoria(categoria);
        return productoMapper.
                ProductotoDto(productoRepository.
                        save(productoMapper.ProductoDtoToProducto(dto)));
    }

    @Override
    public ProductoDto actualizarProducto(ProductoDto dto, Long categoriaId) {
        Producto producto = productoRepository.findById(dto.getId())
                .orElseThrow(()-> new EntityNotFoundException("No existe el producto"));
        Categoria categoria= categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        producto.setImagen(dto.getImagen());
        producto.setCategoria(categoria);

        return productoMapper.ProductotoDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDto obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::ProductotoDto)
                .orElseThrow(()-> new EntityNotFoundException("No existe el producto"));
    }

    @Override
    public List<ProductoDto> listarProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::ProductotoDto)
                .toList();
    }
}
