package com.example.orders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders") // NOMBRE DE DB
@Getter // METODO GET
@Setter // METODO SET
@AllArgsConstructor // CONSTRUCTOR CON ARGUMENTOS
@NoArgsConstructor // CONSTRUCTOR SIN ARGUMENTOS
@Builder // DISENO BUILDER

public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SE GENERA ID
    private Long id;

    @NotNull(message = "Este campo no puede estar vacío")
    private Long productIds; // LISTA DE ORDENES

    @NotBlank(message = "Este campo no puede estar vacío")
    private String customerName;

    @NotNull(message = "Este campo no puede ser nulo")
    @DecimalMin(value = "0.0", message = "Debe ser mayor o igual a cero")
    private Double totalAmount;

    private Boolean status;
}

