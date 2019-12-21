package com.romanov.model.staff;

import com.romanov.model.utils.Address;
import com.romanov.model.utils.Person;
import com.romanov.model.utils.PersonRole;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "hospital_staff")
public class Member extends Person {

    @Column(name = "person_role")
    private PersonRole personRole;

    Member(String firstName, String lastName, int age, String email, String phone, List<Address> addresses, PersonRole personRole)
    {
        super(firstName, lastName, age, email, phone, addresses);
        this.personRole = personRole;
    }

    Member()
    {
        super();
    }

}
