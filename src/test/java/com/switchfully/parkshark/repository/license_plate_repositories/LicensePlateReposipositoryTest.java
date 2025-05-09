package com.switchfully.parkshark.repository.license_plate_repositories;

import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class LicensePlateReposipositoryTest {

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    @Autowired
    private CountryCodeRepository countryCodeRepository;

    @BeforeEach
    void setup() {
        countryCodeRepository.save(new CountryCode("IT", "Italy"));
    }

    @Test
    void saveLicensePlateAndFindById() {
        CountryCode italy = countryCodeRepository.findById("IT").orElseThrow();
        licensePlateRepository.save(new LicensePlate("1-ELF-456", italy));

        LicensePlate found = licensePlateRepository.findById("1-ELF-456").orElseThrow();
        assertEquals("IT", found.getCountryCode().getCode());
    }

    @Test
    void findByNonExistingPlateReturnsEmpty() {
        assertTrue(licensePlateRepository.findById("0-XXX-XXX").isEmpty());
    }
}
