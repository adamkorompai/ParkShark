package com.switchfully.parkshark.domain.license_plate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country_code")
public class CountryCode {

    @Id
    @Column(name = "code", length = 5)
    private String code;

    @Column(name = "country_name", nullable = false, length = 50)
    private String countryName;

    public CountryCode() {
    }

    public CountryCode(String code, String countryName) {
        this.code = code;
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return countryName + ", " + code;
    }
}
