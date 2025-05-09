package com.switchfully.parkshark.domain.dtos;

import java.time.LocalDateTime;

public class MemberOverviewDTO {

    private Long id;
    private String fistName;
    private String lastName;
    private String licensePlateNumber;
    private String telephoneNumber;
    private String emailAddress;
    private LocalDateTime registrationDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
