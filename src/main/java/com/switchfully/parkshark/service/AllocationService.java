package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Allocation;
import com.switchfully.parkshark.domain.AllocationStatus;
import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.dtos.AllocationDto;
import com.switchfully.parkshark.domain.dtos.CreateAllocationDto;
import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.repository.AllocationRepository;
import com.switchfully.parkshark.repository.ParkingLotRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.CountryCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.LicensePlateRepository;
import com.switchfully.parkshark.repository.user_repositories.UserRepository;
import com.switchfully.parkshark.service.mappers.AllocationMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.yaml.snakeyaml.tokens.Token.ID.Value;

@Service
@Transactional
public class AllocationService {

    private AllocationRepository allocationRepository;
    private UserRepository userRepository;
    private ParkingLotRepository parkingLotRepository;
    private LicensePlateRepository licensePlateRepository;
    private AllocationMapper allocationMapper;
    private ParkingLotService parkingLotService;

    @Autowired
    public AllocationService(AllocationRepository allocationRepository, UserRepository userRepository, LicensePlateRepository licensePlateRepository, ParkingLotRepository parkingLotRepository, AllocationMapper allocationMapper, ParkingLotService parkingLotService) {
        this.allocationRepository = allocationRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.allocationMapper = allocationMapper;
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotService = parkingLotService;
    }

    public AllocationDto createAllocation(CreateAllocationDto createAllocationDto) {
        User user = checkValidUser(createAllocationDto);
        LicensePlate licensePlate = checkValidLicensePlate(user, createAllocationDto);
        if (licensePlate == null) {
            throw new EntityNotFoundException("License plate not found");
        }
        ParkingLot parkingLot = checkValidParkingLot(createAllocationDto);
        if (parkingLot == null) {
            throw new RuntimeException("Parking lot already full");
        }
        Allocation allocation = allocationMapper.fromDto(user, parkingLot, licensePlate);
        parkingLotService.decreaseCapacityByOne(parkingLot.getId());
        allocationRepository.save(allocation);
        return allocationMapper.toDto(allocation);
    }

    private User checkValidUser(CreateAllocationDto createAllocationDto) {
        return userRepository.findById(createAllocationDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User with id " + createAllocationDto.getUserId() + " not found"));
    }
    private LicensePlate checkValidLicensePlate(User user, CreateAllocationDto createAllocationDto) {
        LicensePlate userLicensePlate = user.getLicensePlate();
        if (userLicensePlate.getPlateNumber().equals(createAllocationDto.getLicensePlate())) {
            return userLicensePlate;
        }
        return null;
    }

    private ParkingLot checkValidParkingLot(CreateAllocationDto createAllocationDto) {
        ParkingLot parkingLot = parkingLotRepository.findById(createAllocationDto.getParkingLotId()).orElse(null);
        if (parkingLot == null) {
            throw new EntityNotFoundException("Parking lot not found");
        }
        if (!checkParkingLotCapacity(parkingLot)){
            return null;
        }
        return parkingLot;
    }

    private boolean checkParkingLotCapacity(ParkingLot parkingLot) {
        return parkingLot.getCapacity() > 0;
    }

    public AllocationDto stopAllocation(Long id) {
        Allocation allocation = allocationRepository.findById(id).orElse(null);
        if (allocation == null) {
            throw new EntityNotFoundException("Allocation with id " + id + " not found");
        }
        if (!checkAllocationStatus(allocation.getStatus())) {
            return null;
        }
        allocation.setStatus(AllocationStatus.FINISHED);
        allocation.setEndTime(LocalDateTime.now());
        parkingLotService.decreaseCapacityByOne(allocation.getParkingLot().getId());
        allocationRepository.save(allocation);
        return allocationMapper.toDto(allocation);
    }

    private boolean checkAllocationStatus(AllocationStatus allocationStatus) {
        return allocationStatus.toString().equals(AllocationStatus.ACTIVE.toString());
    }
}
