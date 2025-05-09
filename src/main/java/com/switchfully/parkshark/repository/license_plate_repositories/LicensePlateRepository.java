package com.switchfully.parkshark.repository.license_plate_repositories;

import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicensePlateRepository extends JpaRepository<LicensePlate, String> {
}
