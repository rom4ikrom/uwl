package com.romanov.model.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class Person implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    private String email;
    private String phone;

    @Column(name = "person_role")
    private PersonRole personRole;

    Person(String firstName, String lastName, int age, Address address, String email, String phone, PersonRole personRole)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.personRole = personRole;
    }

    Person() {}

}
