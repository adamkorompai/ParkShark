package com.switchfully.parkshark.repository.user_repositories;

import com.switchfully.parkshark.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String emailAddress);
}
