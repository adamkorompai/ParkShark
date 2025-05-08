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
    private Long addressId;

    @NotBlank
    private String plateNumber;

    @NotBlank
    private String membershipLevel;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String firstName, String lastName, String email, String telephoneNumber, String mobileNumber, Long addressId, String plateNumber, String membershipLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.addressId = addressId;
        this.plateNumber = plateNumber;
        this.membershipLevel = membershipLevel;
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

    public Long getAddressId() {
        return addressId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }
}
