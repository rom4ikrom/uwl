package com.romanov.model.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.Address;
import com.romanov.model.utils.PersonRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "surgeon")
public class Surgeon extends Member {

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_treatment_id")
    @JsonIgnore
    private Treatment treatment;

    public Surgeon(String firstName, String lastName, int age, String email, String phone, PersonRole personRole)
    {
        super(firstName, lastName, age, email, phone, personRole);
    }

    public Surgeon(Surgeon surgeon)
    {
        super(surgeon);
    }

    Surgeon() {}

}
