package com.example.deliveries.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "deliveries") // NOMBRE DE LA BD
@Getter // METODOS GET
@Setter // METODOS SET
@AllArgsConstructor // GENERAR CONSTRUCTORES CON ARGUMENTOS
@NoArgsConstructor // GENERAR CONSTRUCTORES SIN ARGUMENTOS
@Builder // DISENO BUILDER

public class DeliveriesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GENERAR ID
    private Long id;

    @NotNull(message = "Este campo no puede estar vacío")
    private Long productIds; // ID DE ENVIOS

    @NotBlank(message = "Este campo no puede estar vacío")
    private String customerName;

    @NotNull(message = "Este campo no puede ser nulo")
    @DecimalMin(value = "0.0", message = "Debe ser mayor o igual a cero")
    private Double totalAmount;

    private Boolean status;
}

