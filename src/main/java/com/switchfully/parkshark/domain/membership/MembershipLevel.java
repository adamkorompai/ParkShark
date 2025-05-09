package com.switchfully.parkshark.domain.membership;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "membership_leveL")
public class MembershipLevel {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 50)
    private MembershipType name;

    @Column(name = "monthly_cost", nullable = false)
    private double monthlyCost;

    @Column(name = "reduction_percent")
    private int reductionPercent;

    @Column(name = "max_parking_duration")
    private int maxParkingDuration;

    public MembershipLevel() {
    }

    public MembershipLevel(MembershipType name, double monthlyCost, int reductionPercent, int maxParkingDuration) {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.reductionPercent = reductionPercent;
        this.maxParkingDuration = maxParkingDuration;
    }

    public MembershipType getName() {
        return name;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public int getReductionPercent() {
        return reductionPercent;
    }

    public int getMaxParkingDuration() {
        return maxParkingDuration;
    }

    public void setMonthlyCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public void setReductionPercent(int reductionPercent) {
        this.reductionPercent = reductionPercent;
    }

    public void setMaxParkingDuration(int maxParkingDuration) {
        this.maxParkingDuration = maxParkingDuration;
    }

    @Override
    public String toString() {
        return "Membership " + name + ", " + monthlyCost + "/month";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MembershipLevel that = (MembershipLevel) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
