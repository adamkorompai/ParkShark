package com.switchfully.parkshark.repository.user_repositories;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.membership.MembershipType;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.repository.AddressRepository;
import com.switchfully.parkshark.repository.PostalCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.CountryCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.LicensePlateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Autowired
    private MembershipLevelRepository membershipLevelRepository;

    private CountryCode countryCode;
    private LicensePlate licensePlate;
    private Address address;
    private MembershipLevel membershipLevel;

    @BeforeEach
    void setUp() {
        countryCode = countryCodeRepository.save(new CountryCode("IT", "Italy"));

        licensePlate = licensePlateRepository.save(
                new LicensePlate("1-ELF-456", countryCode)
        );

        PostalCode postCode = new PostalCode("1040", "Etterbeek");
        postCode = postalCodeRepository.save(postCode);

        address = addressRepository.save(new Address("Avenue des Nerviens", "65", postCode));

        membershipLevel = membershipLevelRepository.findById(MembershipType.SILVER)
                .orElseThrow(() -> new RuntimeException("membership level not found"));
    }

    @Test
    void givenValidDependencies_whenSavingUser_thenUserIsPersistedAndRetrievable() {
        User user = new User(
                "a",
                "b",
                "a@b.co",
                "password",
                null,
                "0478329293",
                address,
                licensePlate,
                membershipLevel
        );

        userRepository.save(user);

        User found = userRepository.findById(user.getId()).orElseThrow();

        assertThat(found.getFirstName()).isEqualTo("a");
        assertThat(found.getAddress().getStreetName()).isEqualTo("Avenue des Nerviens");
        assertThat(found.getLicensePlate().getPlateNumber()).isEqualTo("1-ELF-456");
        assertThat(found.getMembershipLevel().getName().name()).isEqualTo("SILVER");
    }

    @Test
    void givenUser_whenFindByEmail_thenUserIsFound() {
        User user = new User(
                "a",
                "b",
                "a@b.co",
                "password",
                null,
                "0478329293",
                address,
                licensePlate,
                membershipLevel
        );

        userRepository.save(user);

        User found = userRepository.findByEmail(user.getEmail()).orElseThrow();

        assertThat(found.getFirstName()).isEqualTo("a");
        assertThat(found.getAddress().getStreetName()).isEqualTo("Avenue des Nerviens");
        assertThat(found.getLicensePlate().getPlateNumber()).isEqualTo("1-ELF-456");
        assertThat(found.getMembershipLevel().getName().name()).isEqualTo("SILVER");
    }
}
