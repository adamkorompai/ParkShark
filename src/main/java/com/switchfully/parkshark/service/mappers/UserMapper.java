package com.switchfully.parkshark.service.mappers;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.dtos.MemberOverviewDTO;
import com.switchfully.parkshark.domain.dtos.UserDTO;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.user.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTelephoneNumber(),
                user.getMobileNumber(),
                user.getRegistrationDate().format(FORMATTER),
                user.getLicensePlate().getPlateNumber(),
                user.getMembershipLevel().getName().name()
        );
    }

    public User fromCreateUserDTO(CreateUserDTO dto, Address address, LicensePlate licensePlate, MembershipLevel membershipLevel) {
        return new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getTelephoneNumber(),
                dto.getMobileNumber(),
                address,
                licensePlate,
                membershipLevel
        );
    }

    public MemberOverviewDTO toMemberOverviewDTO(User user) {
        MemberOverviewDTO dto = new MemberOverviewDTO();
        dto.setId(user.getId());
        dto.setFistName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setLicensePlateNumber(user.getLicensePlate().getPlateNumber());
        dto.setTelephoneNumber(user.getTelephoneNumber());
        dto.setEmailAddress(user.getEmail());
        dto.setRegistrationDate(user.getRegistrationDate());
        return dto;
    }
}
