package com.tienda.backendinventarioproductos.service;

import com.tienda.backendinventarioproductos.data.ProductoRepository;
import com.tienda.backendinventarioproductos.model.pojo.Producto;
import com.tienda.backendinventarioproductos.model.request.CrearProductoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductosService {

	private final ProductoRepository repository;

	public List<Producto> obtenerProductos() {

		List<Producto> productos = repository.findByActivo(true);
		return productos.isEmpty() ? null : productos;
	}

	public Producto obtenerProducto(String productoId) {
		return repository.findById(Long.valueOf(productoId)).orElse(null);
	}

	public Boolean eliminarProducto(String productoId) {

		Producto producto = repository.findById(Long.valueOf(productoId)).orElse(null);

		if (producto != null) {
			producto.setActivo(false);
			repository.save(producto);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}

	public Producto crearProducto(CrearProductoRequest request) {
		if (request != null && StringUtils.hasLength(request.getCodigo().trim())
				&& StringUtils.hasLength(request.getNombre().trim())
				&& !request.getPrecio().isNaN() && !request.getStock().isNaN() && request.getActivo() != null) {

			Producto producto = Producto.builder().codigo(request.getCodigo()).nombre(request.getNombre())
					.precio(request.getPrecio()).imagen(request.getImagen()).stock(request.getStock())
					.activo(request.getActivo()).build();

			return repository.save(producto);
		} else {
			return null;
		}
	}

	public Producto editarProducto(CrearProductoRequest request, String productoId) {
		Producto producto = repository.findById(Long.valueOf(productoId)).orElse(null);
		if (request != null && StringUtils.hasLength(request.getCodigo().trim())
				&& StringUtils.hasLength(request.getNombre().trim())
				&& !request.getPrecio().isNaN() && !request.getStock().isNaN() && request.getActivo() != null&&producto!=null) {
			Producto productoEditado = Producto.builder().codigo(request.getCodigo()).nombre(request.getNombre())
					.precio(request.getPrecio()).imagen(request.getImagen()).stock(request.getStock())
					.activo(request.getActivo()).build();
			return repository.save(productoEditado);
		} else {
			return null;
		}
	}


}
