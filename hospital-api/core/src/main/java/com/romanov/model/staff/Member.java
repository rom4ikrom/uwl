package com.romanov.model.staff;

import com.romanov.model.record.MedicalRecord;
import com.romanov.model.utils.Address;
import com.romanov.model.utils.Person;
import com.romanov.model.utils.PersonRole;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Member extends Person {

    @Column(name = "person_role")
    private PersonRole personRole;

    Member(String firstName, String lastName, int age, String email, String phone, PersonRole personRole)
    {
        super(firstName, lastName, age, email, phone);
        this.personRole = personRole;
        this.setAddresses(new ArrayList<>());
    }

    Member(Member member)
    {
        super(member.getFirstName(), member.getLastName(), member.getAge(), member.getEmail(), member.getPhone());
        this.personRole = member.getPersonRole();
    }

    Member()
    {
        super();
    }

    @Override
    public String toString()
    {
        return "Member: {}";
    }

}
