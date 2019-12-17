package com.romanov.model.staff;

import javax.persistence.Entity;

@Entity
public class Surgeon extends Person {

    public Surgeon(String firstName, String lastName, int age, Address address, String email, String phone, PersonRole personRole) {
        super(firstName, lastName, age, address, email, phone, personRole);
    }

    private Surgeon() {
        super();
    }

}
