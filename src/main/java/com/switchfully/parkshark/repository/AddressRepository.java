package com.switchfully.parkshark.repository;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> getAddressesByStreetName(String streetName);
    Optional<Address> getAddressesByStreetNameAndStreetNumberAndPostalCode(String streetName, String streetNumber, PostalCode postalCode);
}
