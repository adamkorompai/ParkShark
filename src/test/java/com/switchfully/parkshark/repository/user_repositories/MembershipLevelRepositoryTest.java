package com.switchfully.parkshark.repository.user_repositories;

import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.membership.MembershipType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MembershipLevelRepositoryTest {

    @Autowired
    private MembershipLevelRepository membershipLevelRepository;

    @Test
    void findAndOverwriteMembershipLevelById() {
        MembershipLevel existing = membershipLevelRepository.findById(MembershipType.BRONZE).orElseThrow();

        existing.setMonthlyCost(99);
        existing.setReductionPercent(99);
        existing.setMaxParkingDuration(99);

        membershipLevelRepository.save(existing);

        MembershipLevel found = membershipLevelRepository.findById(MembershipType.BRONZE).orElseThrow();

        assertThat(found.getMonthlyCost()).isEqualTo(99);
        assertThat(found.getReductionPercent()).isEqualTo(99);
        assertThat(found.getMaxParkingDuration()).isEqualTo(99);
    }
}
