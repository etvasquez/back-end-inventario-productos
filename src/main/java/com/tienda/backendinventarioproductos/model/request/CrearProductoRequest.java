package com.tienda.backendinventarioproductos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearProductoRequest {

	private String codigo;
	private String nombre;
	private Double precio;
	private byte[] imagen;
	private Double stock;
	private Boolean activo;

}
