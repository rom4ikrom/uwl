package com.romanov.model.client;

import com.romanov.model.utils.Address;
import com.romanov.model.utils.Person;

import java.util.List;

public class Client extends Person {

    Client(String firstName, String lastName, int age, String email, String phone) {
        super(firstName, lastName, age, email, phone);
    }

    Client()
    {
        super();
    }
}
