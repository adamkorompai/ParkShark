package com.switchfully.parkshark.domain.membership;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membership_level")
public class MembershipLevel {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "monthly_cost")
    private double monthlyCost;

    @Column(name = "reduction_percent")
    private int reductionPercent;

    @Column(name = "max_parking_duration")
    private int maxParkingDuration;

    public MembershipLevel() {
    }

    public MembershipLevel(String name, double monthlyCost, int reductionPercent, int maxParkingDuration) {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.reductionPercent = reductionPercent;
        this.maxParkingDuration = maxParkingDuration;
    }

    public String getName() {
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

    @Override
    public String toString() {
        return "Membership " + name + ", " + monthlyCost + "/month";
    }
}
