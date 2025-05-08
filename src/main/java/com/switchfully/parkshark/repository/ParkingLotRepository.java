package com.switchfully.parkshark.repository;

import com.switchfully.parkshark.domain.ParkingLot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot,Integer> {
}
