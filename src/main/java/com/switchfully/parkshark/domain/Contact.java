package com.switchfully.parkshark.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    @SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
