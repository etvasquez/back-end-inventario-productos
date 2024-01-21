package com.tienda.backendinventarioproductos.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "codigo", unique = true)
	private String codigo;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "precio")
	private Double precio;

	@Lob @Column(name = "imagen")
	private byte[] imagen;
	
	@Column(name = "stock")
	private Double stock;
	
	@Column(name = "activo")
	private Boolean activo;

}
