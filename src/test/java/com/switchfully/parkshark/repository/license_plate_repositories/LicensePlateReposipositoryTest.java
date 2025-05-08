package com.switchfully.parkshark.repository.license_plate_repositories;

import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LicensePlateReposipositoryTest {

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    @Test
    void saveLicensePlate() {
        licensePlateRepository.save(new LicensePlate("1-ELF-456", new CountryCode("IT", "Italy")));
    }
}
