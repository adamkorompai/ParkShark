package com.switchfully.parkshark.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_code_seq")
    @SequenceGenerator(name = "postal_code_seq", sequenceName = "postal_code_seq", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    @Enumerated
    private ParkingCategory category;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    //TODO 1 : Add the relation between the contact id and parking lot in the database
//    @ManyToOne
//    @JoinColumn(name="contact_person_id")
//    private Contact contact;

    //TODO 2 : Add the division in the domain package as a java class
//    @ManyToOne
//    @JoinColumn(name="division_id")
//    private Contact contact;


    public ParkingLot() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ParkingCategory getCategory() {
        return category;
    }

    public void setCategory(ParkingCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
