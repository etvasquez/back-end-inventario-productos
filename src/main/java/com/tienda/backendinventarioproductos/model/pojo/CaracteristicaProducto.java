package com.tienda.backendinventarioproductos.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "caracteristica")
    private String caracteristica;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto", nullable = false)
    @JsonIgnore
    private Producto producto;
}
