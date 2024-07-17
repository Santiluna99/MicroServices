package com.example.deliveries.repository;

import com.example.deliveries.model.DeliveriesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveriesRepository extends JpaRepository<DeliveriesModel, Long> {
}
//METODOS CRUDS
/////////////////////<<Recordar que long es el tipo de la clave primaria de la entidad DeliveriesModel>>>////////////////