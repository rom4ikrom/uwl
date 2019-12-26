package com.romanov.model.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.Address;
import com.romanov.model.utils.PersonRole;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "consultant")
public class Consultant extends Member {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    @JsonIgnore
    private Treatment treatment;

    public Consultant(String firstName, String lastName, int age, String email, String phone, PersonRole personRole)
    {
        super(firstName, lastName, age, email, phone, personRole);
    }

    private Consultant() {
        super();
    }

}
