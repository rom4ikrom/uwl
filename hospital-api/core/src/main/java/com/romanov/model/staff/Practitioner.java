package com.romanov.model.staff;

import com.romanov.model.Request;
import com.romanov.model.record.MedicalRecord;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.Address;
import com.romanov.model.utils.PersonRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "practitioner")
public class Practitioner extends Member {

    @OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @OneToMany(mappedBy = "maker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Treatment> treatments = new ArrayList<>();

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    public Practitioner(String firstName, String lastName, int age, String email, String phone, PersonRole personRole)
    {
        super(firstName, lastName, age, email, phone, personRole);
    }

    private Practitioner() {
        super();
    }

}
