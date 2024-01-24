package com.tienda.backendinventarioproductos.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import com.tienda.backendinventarioproductos.model.pojo.Producto;
import com.tienda.backendinventarioproductos.model.request.CrearProductoRequest;
import com.tienda.backendinventarioproductos.service.ProductosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequiredArgsConstructor
@Slf4j
//@Tag(name = "Productos Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre productos alojados en una base de datos mysql.")
public class ProductosController {

	private final ProductosService service;

	@GetMapping("/productos")
	@Operation(
			operationId = "Obtener productos",
			description = "Operacion de lectura",
			summary = "Se devuelve una lista de todos los productos almacenados en la base de datos.")
	@ApiResponse(
			responseCode = "200",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
	public ResponseEntity<List<Producto>> obtenerProductos(
			@RequestHeader Map<String, String> headers,
			@Parameter(name = "codigo", description = "Código del producto. No tiene por que ser exacta", example = "001", required = false)
			@RequestParam(required = false) String codigo,
			@Parameter(name = "nombre", description = "Nombre del producto. No tiene por que ser exacta", example = "Tablero", required = false)
			@RequestParam(required 	= false) String nombre,
			@Parameter(name = "precio", description = "Precio del producto. Debe ser exacto", example = "20", required = false)
			@RequestParam(required = false) String precio,
			@Parameter(name = "stock", description = "Stock del producto. Debe ser exacto", example = "5", required = false)
			@RequestParam(required = false) String stock,
			@Parameter(name = "activo", description = "Estado del producto. true o false", example = "true", required = false)
			@RequestParam(required = false) Boolean activo) {

		log.info("headers: {}", headers);
		List<Producto> products = service.obtenerProductos(codigo, nombre, precio, stock,activo);

		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/productos/{productoId}")
	/*@Operation(
			operationId = "Obtener un producto",
			description = "Operacion de lectura",
			summary = "Se devuelve un producto a partir de su identificador.")
	@ApiResponse(
			responseCode = "200",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
	@ApiResponse(
			responseCode = "404",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
			description = "No se ha encontrado el producto con el identificador indicado.")*/
	public ResponseEntity<Producto> obtenerProducto(@PathVariable String productoId) {

		log.info("RSolicitud recibida para el producto {}", productoId);
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
