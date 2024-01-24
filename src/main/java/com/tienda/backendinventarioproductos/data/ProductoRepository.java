package com.tienda.backendinventarioproductos.data;

import com.tienda.backendinventarioproductos.data.utils.SearchCriteria;
import com.tienda.backendinventarioproductos.data.utils.SearchOperation;
import com.tienda.backendinventarioproductos.data.utils.SearchStatement;
import com.tienda.backendinventarioproductos.model.pojo.Producto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductoRepository {

    private final ProductoJpaRepository repository;

    public List<Producto> getProducts() {
        return repository.findAll();
    }

    public Producto getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public void delete(Producto producto) {
        repository.delete(producto);
    }

    public List<Producto> search(String codigo, String nombre, String precio, String stock, Boolean activo) {
        SearchCriteria<Producto> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(codigo)) {
            spec.add(new SearchStatement("codigo", codigo, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(nombre)) {
            spec.add(new SearchStatement("nombre", nombre, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(precio)) {
            spec.add(new SearchStatement("precio", precio, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(stock)) {
            spec.add(new SearchStatement("stock", stock, SearchOperation.EQUAL));
        }

        if (activo != null) {
            spec.add(new SearchStatement("activo", activo, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }

}
