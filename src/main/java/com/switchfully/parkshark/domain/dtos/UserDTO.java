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

    public UserDTO() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
}
