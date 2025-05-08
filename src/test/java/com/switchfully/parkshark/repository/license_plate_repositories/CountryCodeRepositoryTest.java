package com.switchfully.parkshark.repository.license_plate_repositories;

import com.switchfully.parkshark.domain.license_plate.CountryCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CountryCodeRepositoryTest {

    @Autowired
    private CountryCodeRepository countryCodeRepository;


    @Test
    void saveCountryCode() {
        countryCodeRepository.save(new CountryCode("IT", "Italy"));
    }

    @Test
    void findByCountryCode() {}
}
