package com.example.deliveries.service;

import com.example.deliveries.model.DeliveriesModel;
import com.example.deliveries.repository.DeliveriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveriesService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final DeliveriesRepository deliveriesRepository;

    // TRAER ENVIOS
    public List<DeliveriesModel> getDeliveries() {
        return deliveriesRepository.findAll();
    }

    // CREAR ENVIO
    public ResponseEntity<Object> createDelivery(DeliveriesModel delivery) {
        Long productIds = delivery.getProductIds();
        String url = "http://localhost:8080/api/products/" + productIds;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                deliveriesRepository.save(delivery);
                return new ResponseEntity<>("Orden creada", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Producto no encontrado, orden no creada", HttpStatus.NOT_FOUND);
            }
        } catch (HttpClientErrorException.NotFound ex) {
            return new ResponseEntity<>("Producto no encontrado, orden no creada", HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE ENVIO
    public ResponseEntity<Object> updateDelivery(Long id, DeliveriesModel updatedDelivery) {
        Optional<DeliveriesModel> existingDeliveryOptional = deliveriesRepository.findById(id);
        if (existingDeliveryOptional.isPresent()) {
            DeliveriesModel existingDelivery = existingDeliveryOptional.get();
            existingDelivery.setProductIds(updatedDelivery.getProductIds());
            existingDelivery.setCustomerName(updatedDelivery.getCustomerName());
            existingDelivery.setHome(updatedDelivery.getHome());
            existingDelivery.setTotalAmount(updatedDelivery.getTotalAmount());
            existingDelivery.setStatus(updatedDelivery.getStatus());

            deliveriesRepository.save(existingDelivery);

            return new ResponseEntity<>("Orden actualizada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Orden no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // ELIMINAR ENVIO
    public ResponseEntity<Object> deleteDelivery(Long id) {
        Optional<DeliveriesModel> existingDeliveryOptional = deliveriesRepository.findById(id);
        if (existingDeliveryOptional.isPresent()) {
            deliveriesRepository.deleteById(id);
            return new ResponseEntity<>("Orden eliminada satisfactoriamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Orden no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // BUSCAR ENVIO POR ID
    public ResponseEntity<Object> findDelivery(Long id) {
        Optional<DeliveriesModel> existingDeliveryOptional = deliveriesRepository.findById(id);
        if (existingDeliveryOptional.isPresent()) {
            DeliveriesModel existingDelivery = existingDeliveryOptional.get();
            return new ResponseEntity<>(existingDelivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Orden no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
