package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.ParkingLotDto;
import com.switchfully.parkshark.repository.ParkingLotRepository;
import com.switchfully.parkshark.service.mappers.ParkingLotMappers;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParkingLotService {

    private final ParkingLotRepository repository;
    private final ParkingLotMappers mappers;

    public ParkingLotService(ParkingLotRepository repository, ParkingLotMappers mappers) {
        this.repository = repository;
        this.mappers = mappers;
    }

    public ParkingLot createParkingLot(ParkingLot parkingLot) {
        if (parkingLot.getCapacity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Capacity must be greater than 0");
        }
        if (parkingLot.getPricePerHour() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PricePerHour must be greater than 0");
        }
        //TODO 1 : When the contact will be set we have to
        // Check if it is not null
        // add the email format verification
        // Also the number verification
        // if the contact exist in the db
        // if not creating it
        return repository.save(parkingLot);

    }

    public ParkingLot getParkingLot(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking lot with id " + id + " not found")
        );
    }

    public List<ParkingLotDto> getAllParkingLots() {
        ArrayList<ParkingLot> parkingLots = (ArrayList<ParkingLot>) repository.findAll();

        return parkingLots.stream().map(
                mappers::toDto
        ).toList();
    }
}
