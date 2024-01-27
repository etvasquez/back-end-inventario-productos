package com.tienda.backendinventarioproductos.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
	private List<CaracteristicaProducto> caracteristicas=new ArrayList<>();

	public void agregarCaracteristica(CaracteristicaProducto caracteristica) {
		caracteristica.setProducto(this);
		caracteristicas.add(caracteristica);
	}
}
