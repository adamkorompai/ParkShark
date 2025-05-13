package com.switchfully.parkshark.repository;

import com.switchfully.parkshark.domain.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
}
