package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.CreateParkingLotDto;
import com.switchfully.parkshark.domain.dtos.ParkingLotDto;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.domain.user.UserRole;
import com.switchfully.parkshark.service.AuthenticationService;
import com.switchfully.parkshark.service.ParkingLotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(name = "/parking-lot")
public class ParkingLotController {

    private final ParkingLotService service;
    private final AuthenticationService authenticationService;

    public ParkingLotController(ParkingLotService parkingLotService, AuthenticationService authenticationService) {
        this.service = parkingLotService;
        this.authenticationService = authenticationService;
    }
    //TODO 1 : As a Manager
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLot createParkingLot(@RequestBody CreateParkingLotDto parkingLot, @RequestHeader("Authorization") String authHeader) {
        authenticateManager(authHeader);
        return service.createParkingLot(parkingLot);
    }

    @GetMapping(value ="/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<ParkingLotDto> getAllParkingLots(@RequestHeader("Authorization") String authHeader) {
        authenticateManager(authHeader);
        return service.getAllParkingLots();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLot getParkingLotById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        authenticateManager(authHeader);
        return service.getParkingLot(id);
    }

    private void authenticateManager(String authHeader) {
        User user = authenticationService.authenticate(authHeader);
        if (user.getRole() != UserRole.MANAGER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Manager privileges required");
        }
    }
}
