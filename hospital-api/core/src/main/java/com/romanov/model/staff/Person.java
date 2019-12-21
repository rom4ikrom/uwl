package com.romanov.model.staff;

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

    @Column(name = "person_role")
    private PersonRole personRole;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private List<Address> addresses = new ArrayList<>();

    Person(String firstName, String lastName, int age, String email, String phone, PersonRole personRole, List<Address> addresses)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.personRole = personRole;
        this.addresses = addresses;
    }

    Person() {}

}
