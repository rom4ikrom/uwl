package com.romanov.model.client;

import com.romanov.model.utils.Address;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class Patient extends Client {

    public Patient(String firstName, String lastName, int age,  String email, String phone, List<Address> addresses) {
        super(firstName, lastName, age, email, phone, addresses);
    }

    private Patient()
    {
        super();
    }
}
