package com.switchfully.parkshark.service.mappers;

import com.switchfully.parkshark.domain.Allocation;
import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.AllocationDto;
import com.switchfully.parkshark.domain.dtos.CreateAllocationDto;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class AllocationMapper {

    public AllocationDto toDto(Allocation allocation) {
        AllocationDto allocationDto = new AllocationDto();
        allocationDto.setId(allocation.getId());
        allocationDto.setUserId(allocation.getUser().getId());
        allocationDto.setParkingLotId(allocation.getParkingLot().getId());
        allocationDto.setLicensePlate(allocation.getLicensePlate().getPlateNumber());
        allocationDto.setStatus(allocation.getStatus().toString());
        allocationDto.setStartTime(allocation.getStartTime());
        allocationDto.setEndTime(allocation.getEndTime());
        return allocationDto;
    }

    public Allocation fromDto(User user, ParkingLot parkingLot, LicensePlate licensePlate) {
        return new Allocation(user, parkingLot, licensePlate);
    }
}
