package com.example.deliveries.controller;

import com.example.deliveries.model.DeliveriesModel;
import com.example.deliveries.service.DeliveriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Deliveries") // DEFINIMOS LA RUTA
@RequiredArgsConstructor
public class DeliveriesController {

    private final DeliveriesService DeliveriesService;

    // TRAER ENVIOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeliveriesModel> getDeliveries() {
        return this.DeliveriesService.getDeliveries();
    }

    // AGREGAR ENVIOS
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createDelivery(@Valid @RequestBody DeliveriesModel delivery, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return DeliveriesService.createDelivery(delivery);
    }

    // ACTUALIZAR ENVIO
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDelivery(@PathVariable("id") Long id, @RequestBody DeliveriesModel updatedDelivery) {
        return DeliveriesService.updateDelivery(id, updatedDelivery);
    }

    // ELIMINAR ENVIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDelivery(@PathVariable("id") Long id) {
        return DeliveriesService.deleteDelivery(id);
    }

    // BUSCAR ENVIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdDelivery(@PathVariable("id") Long id) {
        return DeliveriesService.findDelivery(id);
    }
}
