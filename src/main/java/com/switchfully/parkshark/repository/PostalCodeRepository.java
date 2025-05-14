package com.switchfully.parkshark.repository;

import com.switchfully.parkshark.domain.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {
    Optional<PostalCode> getPostalCodeByLabel(String label);
    Optional<PostalCode> getPostalByCode(String code);
}
