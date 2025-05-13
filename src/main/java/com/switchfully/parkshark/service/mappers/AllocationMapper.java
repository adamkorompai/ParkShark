package com.switchfully.parkshark.service.mappers;

import com.switchfully.parkshark.domain.Allocation;
import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.AllocationDto;
import com.switchfully.parkshark.domain.dtos.CreateAllocationDto;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.user.User;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;


@Component
public class AllocationMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AllocationDto toDto(Allocation allocation) {
        return new AllocationDto(
                allocation.getId(),
                allocation.getUser().getId(),
                allocation.getParkingLot().getId(),
                allocation.getLicensePlate().getPlateNumber(),
                allocation.getStatus().toString(),
                allocation.getEndTime() != null ? allocation.getEndTime().format(FORMATTER) : null,
                allocation.getStartTime().format(FORMATTER)
        );
    }

    public Allocation fromDto(User user, ParkingLot parkingLot, LicensePlate licensePlate) {
        return new Allocation(user, parkingLot, licensePlate);
    }
}
