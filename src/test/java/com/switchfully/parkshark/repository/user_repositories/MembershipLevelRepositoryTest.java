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
    void saveAndFindMembershipLevelById() {
        membershipLevelRepository.save(new MembershipLevel(MembershipType.SILVER, 10, 20, 6));

        MembershipLevel found = membershipLevelRepository.findById(MembershipType.SILVER).orElseThrow();

        assertThat(found.getMonthlyCost()).isEqualTo(10.0);
        assertThat(found.getReductionPercent()).isEqualTo(20);
        assertThat(found.getMaxParkingDuration()).isEqualTo(6);
    }
}
