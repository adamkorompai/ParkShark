package com.switchfully.parkshark.domain.dtos;

import com.switchfully.parkshark.domain.user.User;

import java.time.LocalDateTime;

public class CreateAllocationDto {

    private Long userId;
    private Long parkingLotId;
    private String licensePlate;

    public CreateAllocationDto() {}

    public CreateAllocationDto(Long userId, Long parkingLotId, String licensePlate) {
        this.userId = userId;
        this.parkingLotId = parkingLotId;
        this.licensePlate = licensePlate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
