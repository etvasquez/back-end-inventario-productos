package com.tienda.backendinventarioproductos.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.tienda.backendinventarioproductos.model.pojo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombre(String nombre);
    List<Producto> findByActivo(Boolean activo);
}
