package com.romanov.model.staff;

import lombok.Data;

@Data
public abstract class Person {

    private String id;
    private String firstName;
    private String lastName;

    private int age;
    private Address address;

    private String email;
    private String phone;

    private PersonRole personRole;

}
