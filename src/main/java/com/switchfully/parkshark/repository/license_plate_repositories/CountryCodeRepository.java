package com.switchfully.parkshark.repository.license_plate_repositories;

import com.switchfully.parkshark.domain.license_plate.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryCodeRepository extends JpaRepository<CountryCode, String> {
    Optional<CountryCode> findByCode(String code);
}
