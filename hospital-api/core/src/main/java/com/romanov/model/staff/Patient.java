package com.romanov.model.staff;

import javax.persistence.Entity;

@Entity
public class Patient extends Person {

    public Patient(String firstName, String lastName, int age, Address address, String email, String phone, PersonRole personRole) {
        super(firstName, lastName, age, address, email, phone, personRole);
    }

    private Patient()
    {
        super();
    }
}
