package com.switchfully.parkshark.domain;

import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.service.mappers.AllocationMapper;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "allocation")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
    @SequenceGenerator(name = "allocation_seq", sequenceName = "allocation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @ManyToOne
    @JoinColumn(name = "plate_number")
    private LicensePlate licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AllocationStatus status;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    public Allocation() {}

    public Allocation(User user, ParkingLot parkingLot, LicensePlate licensePlate) {
        this.user = user;
        this.parkingLot = parkingLot;
        this.licensePlate = licensePlate;
        this.status = AllocationStatus.ACTIVE;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public AllocationStatus getStatus() {
        return status;
    }

    public void setStatus(AllocationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "id=" + id +
                ", user=" + user +
                ", parkingLot=" + parkingLot +
                ", licensePlate=" + licensePlate +
                ", status=" + status +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                '}';
    }
}
