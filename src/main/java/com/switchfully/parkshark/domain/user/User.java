package com.switchfully.parkshark.domain.user;

import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "telephone_number", length = 50)
    private String telephoneNumber;

    @Column(name = "mobile_number", length = 50)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private UserRole role;

    @Column(name = "registration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationDate;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "address_id", referencedColumn = "id")
//    private Address address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plate_number", referencedColumnName = "plate_number")
    private LicensePlate licensePlate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "membership_level", referencedColumnName = "name")
    private MembershipLevel membershipLevel;

    public User() {
    }

    public User(String firstName, String lastName, String email, String telephoneNumber, String mobileNumber, LocalDateTime registrationDate, LicensePlate licensePlate, MembershipLevel membershipLevel) {
        this.role = UserRole.MEMBER;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.registrationDate = registrationDate;
        this.licensePlate = licensePlate;
        this.membershipLevel = membershipLevel;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    @Override
    public String toString() {
        return "User " + id + " " + firstName + " " + lastName + " " + email;
    }
}
