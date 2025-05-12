package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.membership.MembershipType;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.repository.user_repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private CountryCode countryCode;
    private LicensePlate licensePlate;
    private PostalCode postalCode;
    private Address address;
    private MembershipLevel membershipLevel;

    @BeforeEach
    void setUp() {
        countryCode = new CountryCode("IT", "Italy");

        licensePlate = new LicensePlate("1-ELF-456", countryCode);

        postalCode = new PostalCode("1040", "Etterbeek");

        address = new Address("Avenue des Nerviens", "65", postalCode);

        membershipLevel = new MembershipLevel(MembershipType.SILVER, 10, 20, 6);
    }

    @Test
    void givenValidCredentials_whenAuthenticate_thenReturnUser() {

        User mockUser = new User(
                "a",
                "b",
                "x@y.com",
                "password",
                "027335463",
                "0475638276",
                address,
                licensePlate,
                membershipLevel
        );

        when(userRepository.findByEmail("x@y.com")).thenReturn(Optional.of(mockUser));

        String encoded = authenticationService.encode("x@y.com", "password");

        User result = authenticationService.authenticate(encoded);

        assertNotNull(result);
        assertEquals("x@y.com", result.getEmail());
    }

    @Test
    void givenWrongPassword_whenAuthenticate_thenThrowError() {
        User mockUser = new User(
                "a",
                "b",
                "x@y.com",
                "password",
                "027335463",
                "0475638276",
                address,
                licensePlate,
                membershipLevel
        );

        when(userRepository.findByEmail("x@y.com")).thenReturn(Optional.of(mockUser));

        String encoded = authenticationService.encode("x@y.com", "wrongPassword");


        assertThrows(ResponseStatusException.class, () -> {
            authenticationService.authenticate(encoded);
        });
    }

    @Test
    void givenWrongUser_whenAuthenticate_thenThrowError() {
        when(userRepository.findByEmail("wrong@email.com")).thenReturn(Optional.empty());
        String encoded = authenticationService.encode("wrong@email.com", "who needs it?");

        assertThrows(ResponseStatusException.class, () -> {
            authenticationService.authenticate(encoded);
        });
    }

    @Test
    void givenBadHeader_whenAuthenticate_thenThrowError() {
        String badHeader = "NotBasic base64";

        assertThrows(ResponseStatusException.class, () -> {
            authenticationService.authenticate(badHeader);
        });
    }

}
