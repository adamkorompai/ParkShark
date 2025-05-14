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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PostalCodeRepository postalCodeRepository;
    private final CountryCodeRepository countryCodeRepository;
    private final LicensePlateRepository licensePlateRepository;
    private final MembershipLevelRepository membershipLevelRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       AddressRepository addressRepository,
                       PostalCodeRepository postalCodeRepository,
                       CountryCodeRepository countryCodeRepository,
                       LicensePlateRepository licensePlateRepository,
                       MembershipLevelRepository membershipLevelRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.postalCodeRepository = postalCodeRepository;
        this.countryCodeRepository = countryCodeRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.membershipLevelRepository = membershipLevelRepository;
        this.userMapper = userMapper;
    }

    public List<MemberOverviewDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toMemberOverviewDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        return userMapper.toUserDTO(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public UserDTO registerMember(CreateUserDTO dto) {
        validateCreateUserDTO(dto);

        Address address = registerAddress(dto);

        LicensePlate licensePlate = registerLicensePlate(dto);

        MembershipLevel membershipLevel = findMembershipLevel(dto);

        User user = userMapper.fromCreateUserDTO(dto, address, licensePlate, membershipLevel);
        userRepository.save(user);

        return userMapper.toUserDTO(user);
    }

    private Address registerAddress(CreateUserDTO dto) {
        PostalCode postalCode = postalCodeRepository.getPostalByCode(dto.getPostalCode())
                .orElseGet(() -> postalCodeRepository.save(new PostalCode(dto.getPostalCode(), dto.getCity())));

        Address address = new Address(dto.getStreetName(), dto.getStreetNumber(), postalCode);
        addressRepository.save(address);
        return address;
    }

    private LicensePlate registerLicensePlate(CreateUserDTO dto) {
        CountryCode countryCode = countryCodeRepository.findByCode(dto.getCountryCode())
                .orElseGet(() -> countryCodeRepository.save(new CountryCode(dto.getCountryCode(), dto.getCountryName())));

        LicensePlate licensePlate = new LicensePlate(dto.getPlateNumber(), countryCode);
        licensePlateRepository.save(licensePlate);
        return licensePlate;
    }
    private MembershipLevel findMembershipLevel(CreateUserDTO dto) {
        return membershipLevelRepository.findById(MembershipType.valueOf(dto.getMembershipLevel().toUpperCase()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid membership level: " + dto.getMembershipLevel()));
    }

    private void validateCreateUserDTO(CreateUserDTO dto) {
        validateStringField(dto.getFirstName(), "First name");
        validateStringField(dto.getLastName(), "Last name");
        validateEmail(dto.getEmail());
        validateStringField(dto.getStreetName(), "Street name");
        validateStringField(dto.getStreetNumber(), "Street number");
        validateStringField(dto.getPostalCode(), "Postal code");
        validateStringField(dto.getCity(), "City");
        validateStringField(dto.getCountryCode(), "Country code");
        validateStringField(dto.getCountryName(), "Country name");
        validateStringField(dto.getPlateNumber(), "License plate");
        validateMembershipLevel(dto.getMembershipLevel());
    }

    private void validateStringField(String field, String fieldName) {
        if (field == null || field.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        boolean emailExists = userRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
        if (emailExists) {
            throw new IllegalArgumentException("Email already in use");
        }
    }

    private void validateMembershipLevel(String level) {
        try {
            MembershipType.valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Membership level must be BRONZE, SILVER, or GOLD");
        }
    }
}
