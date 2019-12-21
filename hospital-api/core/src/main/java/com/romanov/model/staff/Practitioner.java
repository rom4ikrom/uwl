package com.romanov.model.staff;

import com.romanov.model.utils.Address;
import com.romanov.model.utils.PersonRole;

import java.util.List;

public class Practitioner extends Member {

    public Practitioner(String firstName, String lastName, int age, String email, String phone, List<Address> addresses, PersonRole personRole)
    {
        super(firstName, lastName, age, email, phone, addresses,  personRole);
    }

    private Practitioner() {
        super();
    }

}
