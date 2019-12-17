package com.romanov.model.staff;

import javax.persistence.Entity;

@Entity
public class Consultant extends Person {

    public Consultant(String firstName, String lastName, int age, Address address, String email, String phone, PersonRole personRole) {
        super(firstName, lastName, age, address, email, phone, personRole);
    }

    private Consultant() {
        super();
    }

}
