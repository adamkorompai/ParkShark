package com.switchfully.parkshark.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String telephoneNumber;
    private String mobileNumber;

    @NotBlank
    private String plateNumber;

    @NotBlank
    private String membershipLevel;

    @NotBlank
    private String streetName;

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String city;

    @NotBlank
    private String countryCode;

    @NotBlank
    private String countryName;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String firstName, String lastName, String email, String telephoneNumber, String mobileNumber, String plateNumber, String membershipLevel, String streetName, String streetNumber, String postalCode, String city, String countryCode, String countryName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.plateNumber = plateNumber;
        this.membershipLevel = membershipLevel;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }
}
