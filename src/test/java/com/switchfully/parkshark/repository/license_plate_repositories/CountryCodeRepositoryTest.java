package com.switchfully.parkshark.repository.license_plate_repositories;

import com.switchfully.parkshark.domain.license_plate.CountryCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CountryCodeRepositoryTest {

    @Autowired
    private CountryCodeRepository countryCodeRepository;


    @Test
    void saveCountryCodeAndFindById() {
        countryCodeRepository.save(new CountryCode("IT", "Italy"));

        CountryCode found = countryCodeRepository.findById("IT").orElseThrow();
        assertEquals("Italy", found.getCountryName());
    }

    @Test
    void findByNonValidCountryCode() {
        assertTrue(countryCodeRepository.findById("ZZ").isEmpty());
    }
}
