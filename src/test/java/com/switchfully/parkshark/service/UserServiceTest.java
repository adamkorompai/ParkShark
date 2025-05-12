package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.dtos.MemberOverviewDTO;
import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.membership.MembershipType;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.repository.AddressRepository;
import com.switchfully.parkshark.repository.PostalCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.CountryCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.LicensePlateRepository;
import com.switchfully.parkshark.repository.user_repositories.MembershipLevelRepository;
import com.switchfully.parkshark.repository.user_repositories.UserRepository;
import com.switchfully.parkshark.service.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private CountryCodeRepository countryCodeRepository;

    @Mock
    private LicensePlateRepository licensePlateRepository;

    @Mock
    private PostalCodeRepository postalCodeRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private MembershipLevelRepository membershipLevelRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private CountryCode countryCode;
    private LicensePlate licensePlate;
    private PostalCode postalCode;
    private Address address;
    private MembershipLevel membershipLevel;
    private User user;

    @BeforeEach
    void setUp() {
        countryCode = countryCodeRepository.save(new CountryCode("IT", "Italy"));

        licensePlate = licensePlateRepository.save(
                new LicensePlate("1-ELF-456", countryCode)
        );

        postalCode = postalCodeRepository.save(new PostalCode("1040", "Etterbeek"));

        address = addressRepository.save(new Address("Avenue des Nerviens", "65", postalCode));

        membershipLevel = membershipLevelRepository.findById(MembershipType.SILVER)
                .orElseThrow(() -> new RuntimeException("me mbership level not found"));

        user = userRepository.save(new User(
                "a",
                "b",
                "a@b.co",
                "password",
                null,
                "0478329293",
                address,
                licensePlate,
                membershipLevel
        ));
    }

    @Test
    void whenGetAllMembers_thenReturnsAllUserDTO() {
        MemberOverviewDTO memberOverviewDTO = new MemberOverviewDTO();
        memberOverviewDTO.setId(user.getId());
        memberOverviewDTO.setFistName("a");
        memberOverviewDTO.setLastName("b");
        memberOverviewDTO.setEmailAddress("a@b.co");
        memberOverviewDTO.setRegistrationDate(user.getRegistrationDate());

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toMemberOverviewDTO(user)).thenReturn(memberOverviewDTO);

        List<MemberOverviewDTO> result = userService.getAllUsers();
        verify(userMapper).toMemberOverviewDTO(user);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(memberOverviewDTO);
    }

    @Test
    void whenGetMemberById_thenReturnsUserDTO() {}
}
