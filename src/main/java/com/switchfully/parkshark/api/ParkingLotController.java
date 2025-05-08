package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.ParkingLotDto;
import com.switchfully.parkshark.service.ParkingLotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/parking-lot")
public class ParkingLotController {

    private final ParkingLotService service;
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.service = parkingLotService;
    }
    //TODO 1 : As a Manager
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot) {
        return service.createParkingLot(parkingLot);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<ParkingLotDto> getAllParkingLots() {
        return service.getAllParkingLots();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLot getParkingLotById(@PathVariable int id) {
        return service.getParkingLot(id);
    }
}
