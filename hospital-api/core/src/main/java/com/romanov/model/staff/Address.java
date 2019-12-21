package com.romanov.model.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    public static final String ADDRESS_LINE1 = "addressLine1";
    public static final String ADDRESS_LINE2 = "addressLine2";

    public static final String TOWN = "town";
    public static final String POSTCODE = "postcode";
    public static final String COUNTRY = "country";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    private String town;
    private String postcode;
    private String country;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Address(String addressLine1, String addressLine2, String town, String postcode, String country)
    {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
        this.country = country;
    }

    Address() {}
}
