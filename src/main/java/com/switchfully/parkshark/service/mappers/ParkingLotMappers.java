package com.switchfully.parkshark.service.mappers;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.ParkingLotDto;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMappers {
    public ParkingLotDto toDto(ParkingLot parkingLot) {
        ParkingLotDto parkingLotDto = new ParkingLotDto();

        parkingLotDto.setId(parkingLot.getId());
        parkingLotDto.setName(parkingLot.getName());
        parkingLotDto.setCapacity(parkingLot.getCapacity());
        parkingLotDto.setContact_email(parkingLot.getContactEmail());
        return parkingLotDto;
    }
}
