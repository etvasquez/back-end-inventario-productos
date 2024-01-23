package com.tienda.backendinventarioproductos.data;

import com.tienda.backendinventarioproductos.model.pojo.CaracteristicaProducto;
import com.tienda.backendinventarioproductos.model.pojo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaractericticaProductoRepository extends JpaRepository<CaracteristicaProducto, Long> {

    List<CaracteristicaProducto> findByProducto(Producto id);

}
