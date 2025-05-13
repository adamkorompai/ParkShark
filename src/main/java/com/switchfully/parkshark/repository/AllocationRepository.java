package com.switchfully.parkshark.repository;

import com.switchfully.parkshark.domain.Allocation;
import com.switchfully.parkshark.domain.AllocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    List<Allocation> findByStatus(AllocationStatus status);
}
