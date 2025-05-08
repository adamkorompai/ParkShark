package com.switchfully.parkshark.repository.user_repositories;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.repository.AddressRepository;
import com.switchfully.parkshark.repository.PostalCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.CountryCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.LicensePlateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryCodeRepository countryCodeRepository;

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Autowired
    private AddressRepository addressRepository;

    private CountryCode countryCode;
    private LicensePlate licensePlate;
    private Address address;

    @BeforeEach
    void setUp() {
        countryCode = new CountryCode("IT", "Italy");
        countryCodeRepository.save(countryCode);

        licensePlate = new LicensePlate("1-ELF-456", countryCode);
        licensePlateRepository.save(licensePlate);

        PostalCode postCode = new PostalCode("1040", "Etterbeek");
        postalCodeRepository.save(postCode);

        address = new Address("Avenue des Nerviens", "65", postCode);
        addressRepository.save(address);
    }
}
