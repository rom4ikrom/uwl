package com.romanov.model.client;

import com.romanov.model.record.MedicalHistory;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.Address;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class Patient extends Client {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "history_id")
    private MedicalHistory medicalHistory = new MedicalHistory();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    public Patient(String firstName, String lastName, int age,  String email, String phone) {
        super(firstName, lastName, age, email, phone);
    }

    public Patient(Patient patient)
    {
        super(patient.getFirstName(), patient.getLastName(), patient.getAge(), patient.getEmail(), patient.getPhone());
        this.setAddresses(new ArrayList<>());
    }

    private Patient()
    {
        super();
    }
}
