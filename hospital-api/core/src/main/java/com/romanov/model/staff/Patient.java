package com.romanov.model.staff;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class Patient extends Person {

    public Patient(String firstName, String lastName, int age,  String email, String phone, PersonRole personRole, List<Address> addresses) {
        super(firstName, lastName, age, email, phone, personRole, addresses);
    }

    private Patient()
    {
        super();
    }
}
