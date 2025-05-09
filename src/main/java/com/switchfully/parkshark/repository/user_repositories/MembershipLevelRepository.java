package com.switchfully.parkshark.repository.user_repositories;

import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.membership.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipLevelRepository extends JpaRepository<MembershipLevel, MembershipType> {
}
