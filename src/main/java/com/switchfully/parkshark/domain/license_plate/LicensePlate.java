package com.switchfully.parkshark.domain.license_plate;

import jakarta.persistence.*;

@Entity
@Table(name = "license_plate")
public class LicensePlate {

    @Id
    @Column(name = "plate_number", length = 50)
    private String plateNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryCode countryCode;

    public LicensePlate() {
    }

    public LicensePlate(String plateNumber, CountryCode countryCode) {
        this.plateNumber = plateNumber;
        this.countryCode = countryCode;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return countryCode + ", " + plateNumber;
    }
}
