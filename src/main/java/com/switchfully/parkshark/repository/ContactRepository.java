package com.switchfully.parkshark.repository;

import com.switchfully.parkshark.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> getContactByName(String name);
    Optional<Contact> getContactByEmail(String email);

}
