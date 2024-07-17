package com.example.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "product") // NOMBRE DB
@Getter // METODO GET
@Setter // METODO SET
@AllArgsConstructor // CONSTRUCTOR CON ARGUMENTOS
@NoArgsConstructor // CONSTRUCTOR SIN ARGUMENTOS
@Builder // DISENO BUILDER

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GENERA ID
    private Long id;

    @NotBlank(message = "Este campo no puede estar vacío")
    private String sku;

    @NotBlank(message = "Este campo no puede estar vacío")
    private String name;

    @NotNull(message = "Este campo no puede ser nulo")
    @DecimalMin(value = "0.0", message = "Debe ser mayor o igual a cero")
    private Double price;

    private Boolean status;
}