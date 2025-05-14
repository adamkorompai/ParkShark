package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.dtos.MemberOverviewDTO;
import com.switchfully.parkshark.domain.dtos.UserDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    private UserService userService;
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
        userService = new UserService(
                userRepository,
                addressRepository,
                postalCodeRepository,
                countryCodeRepository,
                licensePlateRepository,
                membershipLevelRepository,
                userMapper
        );
    }

    @Test
    void whenGetAllMembers_thenReturnsAllUserDTO() {
        CountryCode countryCode = new CountryCode("IT", "Italy");
        LicensePlate licensePlate = new LicensePlate("1-ELF-456", countryCode);
        PostalCode postalCode = new PostalCode("1040", "Etterbeek");
        Address address = new Address("Avenue des Nerviens", "65", postalCode);
        MembershipLevel membershipLevel = new MembershipLevel(MembershipType.SILVER, 10, 20, 6);

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

        user.setId(1L);

        MemberOverviewDTO memberOverviewDTO = new MemberOverviewDTO();
        memberOverviewDTO.setId(1L);
        memberOverviewDTO.setFistName("a");
        memberOverviewDTO.setLastName("b");
        memberOverviewDTO.setEmailAddress("a@b.co");
        memberOverviewDTO.setRegistrationDate(LocalDateTime.now());

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<MemberOverviewDTO> result = userService.getAllUsers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(memberOverviewDTO);
    }

    @Test
    void whenGetMemberById_thenReturnsUserDTO() {
        CountryCode countryCode = new CountryCode("IT", "Italy");
        LicensePlate licensePlate = new LicensePlate("1-ELF-456", countryCode);
        PostalCode postalCode = new PostalCode("1040", "Etterbeek");
        Address address = new Address("Avenue des Nerviens", "65", postalCode);
        MembershipLevel membershipLevel = new MembershipLevel(MembershipType.SILVER, 10, 20, 6);

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

        user.setId(1L);

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName("a");
        dto.setLastName("b");
        dto.setEmail("a@b.co");
        dto.setTelephoneNumber(null);
        dto.setMobileNumber("0478329293");
        dto.setRegistrationDate(user.getRegistrationDate().toString());
        dto.setLicensePlate("1-ELF-456");
        dto.setMembershipLevel("SILVER");


        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        UserDTO result = userService.getUserById(user.getId());

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("a");
        assertThat(result.getLastName()).isEqualTo("b");
        assertThat(result.getEmail()).isEqualTo("a@b.co");
        assertThat(result.getMobileNumber()).isEqualTo("0478329293");
        assertThat(result.getLicensePlate()).isEqualTo("1-ELF-456");
        assertThat(result.getMembershipLevel()).isEqualTo("SILVER");
    }

    @Test
    void givenValidInput_whenRegisterUser_thenReturnsUserDTO() {
        CreateUserDTO dto = new CreateUserDTO(
                "a",
                "b",
                "a@b.co",
                "password",
                null,
                "0478329293",
                "1-ELF-456",
                "SILVER",
                "Rue Bazar",
                "34",
                "1040",
                "Brussels",
                "BE",
                "Belgium"
        );

        PostalCode postalCode = new PostalCode("1040", "Brussels");
        when(postalCodeRepository.getPostalByCode("1040")).thenReturn(Optional.of(postalCode));

        CountryCode countryCode = new CountryCode("BE", "Belgium");
        when(countryCodeRepository.findByCode("BE")).thenReturn(Optional.of(countryCode));

        MembershipLevel membershipLevel = new MembershipLevel(MembershipType.SILVER, 10, 20, 6);
        when(membershipLevelRepository.findById(MembershipType.SILVER)).thenReturn(Optional.of(membershipLevel));

        when(userRepository.findAll()).thenReturn(List.of());

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        UserDTO result = userService.registerMember(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("a");
        assertThat(result.getLastName()).isEqualTo("b");
        assertThat(result.getEmail()).isEqualTo("a@b.co");
        assertThat(result.getMobileNumber()).isEqualTo("0478329293");
        assertThat(result.getLicensePlate()).isEqualTo("1-ELF-456");
        assertThat(result.getMembershipLevel()).isEqualTo("SILVER");

        verify(userRepository).save(any(User.class));
    }
}
