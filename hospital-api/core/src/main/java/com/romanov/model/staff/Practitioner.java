package com.romanov.model.staff;

import javax.persistence.Entity;

@Entity
public class Practitioner extends Person {

    public Practitioner(String firstName, String lastName, int age, String email, String phone, PersonRole personRole, Address address) {
        super(firstName, lastName, age, address, email, phone, personRole);
    }

    private Practitioner() {
        super();
    }

}
