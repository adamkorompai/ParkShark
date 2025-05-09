package com.switchfully.parkshark.domain.dtos;

public class UserDTO {

    public Long id;

    public String firstName;

    public String lastName;

    public String email;

    public String telephoneNumber;

    public String mobileNumber;

    private String registrationDate;

    private String licensePlate;

    private String membershipLevel;

    public UserDTO(Long id, String firstName, String lastName, String email, String telephoneNumber, String mobileNumber, String registrationDate, String licensePlate, String membershipLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.registrationDate = registrationDate;
        this.licensePlate = licensePlate;
        this.membershipLevel = membershipLevel;
    }

    public Long getId() {
        return id;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }
}
