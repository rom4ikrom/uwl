package com.romanov.model.staff;

import lombok.Data;

@Data
public class Address {

    private String id;
    private String addressLine1;
    private String addressLine2;

    private String town;
    private String postcode;
    private String country;
}
