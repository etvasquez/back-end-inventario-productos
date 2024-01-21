package com.tienda.backendinventarioproductos.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "caracteristica_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class CaracteristicaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo", unique = true)
    private String codigo;
}
