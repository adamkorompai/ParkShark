package com.switchfully.parkshark.domain.license_plate;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "license_plate")
public class LicensePlate {

    @Id
    @Column(name = "plate_number", nullable = false, length = 50)
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(plateNumber, that.plateNumber) && Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber, countryCode);
    }
}
