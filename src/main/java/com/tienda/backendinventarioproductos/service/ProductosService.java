package com.tienda.backendinventarioproductos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.backendinventarioproductos.data.CaractericticaProductoRepository;
import com.tienda.backendinventarioproductos.data.ProductoJpaRepository;
import com.tienda.backendinventarioproductos.data.ProductoRepository;
import com.tienda.backendinventarioproductos.model.pojo.CaracteristicaProducto;
import com.tienda.backendinventarioproductos.model.pojo.Producto;
import com.tienda.backendinventarioproductos.model.request.CrearProductoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductosService {

	private final ProductoRepository repository;
	private final CaractericticaProductoRepository repositoryCaracteristicaProducto;


	public List<Producto> obtenerProductos(String codigo, String nombre,String precio, String stock, Boolean activo) {

		if (StringUtils.hasLength(codigo) || StringUtils.hasLength(nombre) || StringUtils.hasLength(precio)
				|| StringUtils.hasLength(stock) || activo != null) {


			return repository.search(codigo, nombre, precio, stock, activo);
		}

		List<Producto> products = repository.getProducts();
		return products.isEmpty() ? null : products;
	}

	public Producto obtenerProducto(String productoId) {
		return repository.getById(Long.valueOf(productoId));
	}

	public Boolean eliminarProducto(String productoId) {

		Producto producto = repository.getById(Long.valueOf(productoId));

		if (producto != null) {
			producto.setActivo(false);
			repository.delete(producto);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}

	public Producto crearProducto(CrearProductoRequest request) {
		if (request != null && StringUtils.hasLength(request.getCodigo().trim())
				&& StringUtils.hasLength(request.getNombre().trim())
				&& !request.getPrecio().isNaN() && !request.getStock().isNaN() && request.getActivo() != null) {

            Producto producto = new Producto();
            producto.setCodigo(request.getCodigo());
            producto.setNombre(request.getNombre());
            producto.setPrecio(request.getPrecio());
            producto.setImagen(request.getImagen());
            producto.setActivo(request.getActivo());
            producto.setStock(request.getStock());

            for (int i = 0; i < request.getCaracteristicas().size(); i++) {
                producto.agregarCaracteristica(request.getCaracteristicas().get(i));
            }

            return repository.save(producto);

		} else {
			return null;
		}
	}

	public Producto editarProducto(CrearProductoRequest request, String productoId) {
		Producto producto = repository.getById(Long.valueOf(productoId));

		if (request != null && StringUtils.hasLength(request.getCodigo().trim())
				&& StringUtils.hasLength(request.getNombre().trim())
				&& !request.getPrecio().isNaN() && !request.getStock().isNaN() && request.getActivo() != null&&producto!=null) {


			List<CaracteristicaProducto> caracteristicaProductoList=repositoryCaracteristicaProducto.findByProducto(producto);

			for (int j = 0; j < caracteristicaProductoList.size(); j++) {
				boolean existe=false;
				for (int i = 0; i < request.getCaracteristicas().size(); i++) {
					if(caracteristicaProductoList.get(j).getId().equals(request.getCaracteristicas().get(i).getId())){
						existe=true;
					}
				}
				if(!existe){
					repositoryCaracteristicaProducto.deleteById(caracteristicaProductoList.get(j).getId());
				}
			}
			producto = repository.getById(Long.valueOf(productoId));
			producto.setCodigo(request.getCodigo());
			producto.setNombre(request.getNombre());
			producto.setPrecio(request.getPrecio());
			producto.setImagen(request.getImagen());
			producto.setActivo(request.getActivo());
			producto.setStock(request.getStock());

			for (int i = 0; i < request.getCaracteristicas().size(); i++) {
				producto.agregarCaracteristica(request.getCaracteristicas().get(i));
			}


			return repository.save(producto);

		} else {
			return null;
		}
	}


}
