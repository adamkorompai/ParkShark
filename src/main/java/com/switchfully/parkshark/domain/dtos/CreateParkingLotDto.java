package com.switchfully.parkshark.domain.dtos;

import com.switchfully.parkshark.domain.ParkingCategory;

public class CreateParkingLotDto {
    private String name;
    private ParkingCategory category;
    private int capacity;
    private double pricePerHour;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String postalCodeLabel;

    private String contact_name;
    private String contact_phoneNumber;
    private String contact_telNumber;
    private String contact_email;

    private String contact_streetName;
    private String contact_streetNumber;
    private String contact_postalCode;
    private String contact_postalCodeLabel;


    private String divisionName;


    public CreateParkingLotDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParkingCategory getCategory() {
        return category;
    }

    public void setCategory(ParkingCategory category) {
        this.category = category;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phoneNumber() {
        return contact_phoneNumber;
    }

    public void setContact_phoneNumber(String contact_phoneNumber) {
        this.contact_phoneNumber = contact_phoneNumber;
    }

    public String getContact_telNumber() {
        return contact_telNumber;
    }

    public void setContact_telNumber(String contact_telNumber) {
        this.contact_telNumber = contact_telNumber;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_streetName() {
        return contact_streetName;
    }

    public void setContact_streetName(String contact_streetName) {
        this.contact_streetName = contact_streetName;
    }

    public String getContact_streetNumber() {
        return contact_streetNumber;
    }

    public void setContact_streetNumber(String contact_streetNumber) {
        this.contact_streetNumber = contact_streetNumber;
    }

    public String getContact_postalCode() {
        return contact_postalCode;
    }

    public void setContact_postalCode(String contact_postalCode) {
        this.contact_postalCode = contact_postalCode;
    }

    public String getContact_postalCodeLabel() {
        return contact_postalCodeLabel;
    }

    public void setContact_postalCodeLabel(String contact_postalCodeLabel) {
        this.contact_postalCodeLabel = contact_postalCodeLabel;
    }

    public String getPostalCodeLabel() {
        return postalCodeLabel;
    }

    public void setPostalCodeLabel(String postalCodeLabel) {
        this.postalCodeLabel = postalCodeLabel;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}
