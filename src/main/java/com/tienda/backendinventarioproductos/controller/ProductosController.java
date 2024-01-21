package com.tienda.backendinventarioproductos.controller;

import com.tienda.backendinventarioproductos.model.pojo.Producto;
import com.tienda.backendinventarioproductos.model.request.CrearProductoRequest;
import com.tienda.backendinventarioproductos.service.ProductosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductosController {

	private final ProductosService service;

	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> obtenerProductos(@RequestHeader Map<String, String> headers) {

		log.info("headers: {}", headers);
		List<Producto> productos = service.obtenerProductos();

		if (productos != null) {
			return ResponseEntity.ok(productos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/productos/{productoId}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable String productoId) {

		log.info("Request received for product {}", productoId);
		Producto producto = service.obtenerProducto(productoId);

		if (producto != null) {
			return ResponseEntity.ok(producto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/productos/{productoId}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable String productoId) {

		Boolean removed = service.eliminarProducto(productoId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/productos")
	public ResponseEntity<Producto> crearProducto(@RequestBody CrearProductoRequest request) {

		Producto producto = service.crearProducto(request);

		if (producto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(producto);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@PutMapping("/productos/{productoId}")
	public ResponseEntity<Producto> editarProducto(@RequestBody CrearProductoRequest request, @PathVariable String productoId) {

		Producto producto = service.editarProducto(request, productoId);

		if (producto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(producto);
		} else {
			return ResponseEntity.badRequest().build();
		}
}
}
