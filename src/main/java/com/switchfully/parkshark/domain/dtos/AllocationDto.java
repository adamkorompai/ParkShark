package com.switchfully.parkshark.domain.dtos;

import com.switchfully.parkshark.domain.AllocationStatus;

import java.time.LocalDateTime;

public class AllocationDto {
    private Long id;
    private Long userId;
    private Long parkingLotId;
    private String licensePlate;
    private String status;
    private LocalDateTime endTime;
    private LocalDateTime startTime;

    public AllocationDto() {}

    public AllocationDto(Long id, Long userId, Long parkingLotId, String licensePlate, String status, LocalDateTime endTime, LocalDateTime startTime) {
        this.id = id;
        this.userId = userId;
        this.parkingLotId = parkingLotId;
        this.licensePlate = licensePlate;
        this.status = status;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
