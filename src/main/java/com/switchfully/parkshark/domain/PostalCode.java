package com.switchfully.parkshark.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "postal_code")
public class PostalCode {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_code_seq")
    @SequenceGenerator(name = "postal_code_seq", sequenceName = "postal_code_seq", allocationSize = 1)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "label")
    private String label;

    public PostalCode(){}

    public PostalCode(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public Long getPostalCode() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
