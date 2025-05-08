package com.switchfully.parkshark.domain.dtos;

public class ParkingLotDto {

    private Long id;
    private String name;
    private int capacity;
    private String contact_email;

    public ParkingLotDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        System.out.println(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }
}
