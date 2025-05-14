package com.switchfully.parkshark.domain.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

public class AllocationDto {
    private Long id;
    private Long userId;
    private Long parkingLotId;
    private String licensePlate;
    private String status;
    private String endTime;
    private String startTime;
//    private String duration;

    public AllocationDto() {}

    public AllocationDto(Long id, Long userId, Long parkingLotId, String licensePlate, String status, String endTime, String startTime) {
        this.id = id;
        this.userId = userId;
        this.parkingLotId = parkingLotId;
        this.licensePlate = licensePlate;
        this.status = status;
        this.endTime = endTime;
        this.startTime = startTime;

//        LocalDateTime parsedStart = LocalDateTime.parse(startTime);
//        LocalDateTime parsedEnd = (endTime != null) ? LocalDateTime.parse(endTime) : LocalDateTime.now();
//
//        Duration duration = Duration.between(parsedStart, parsedEnd);
//        this.duration = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
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

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
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

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
