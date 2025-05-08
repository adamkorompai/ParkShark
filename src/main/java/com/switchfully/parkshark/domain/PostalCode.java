package com.switchfully.parkshark.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "postal_code")
public class PostalCode {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_code_seq")
    @SequenceGenerator(name = "postal_code_seq", sequenceName = "postal_code_seq", allocationSize = 1)
    private int postalCode;

    @Column(name = "code")
    private String code;

    @Column(name = "label")
    private String label;

    public PostalCode(){}

    public int getPostalCode() {
        return postalCode;
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
