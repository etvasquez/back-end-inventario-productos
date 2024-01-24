package com.tienda.backendinventarioproductos.data;

import java.util.List;

import com.tienda.backendinventarioproductos.data.utils.SearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import  com.tienda.backendinventarioproductos.model.pojo.Producto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductoJpaRepository extends  JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {

    List<Producto> findByCodigo(String codigo);

    List<Producto> findByNombre(String nombre);

    List<Producto> findByPrecio(String precio);

    List<Producto> findByStock(String stock);

    List<Producto> findByActivo(Boolean activo);

    List<Producto> findByNombre_AndPrecio(String nombre, String precio);

}
