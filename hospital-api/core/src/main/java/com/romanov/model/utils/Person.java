package com.romanov.model.utils;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String AGE = "age";

    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PERSON_ROLE = "personRole";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int age;

    @Column(name = "email", unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    public Person(String firstName, String lastName, int age, String email, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Person() {}

    @Override
    public String toString()
    {
        return "Person: {}";
    }

    public void addAddress(Address address)
    {
        this.addresses.add(address);
        address.setPerson(this);
    }

    public void addAddresses(List<Address> addresses)
    {
        this.addresses.addAll(addresses);
        addresses.forEach(x -> x.setPerson(this));
    }

    public void removeAddress(Address address)
    {
        this.addresses.remove(address);
        address.setPerson(null);
    }

}
