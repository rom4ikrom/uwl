package com.romanov.model.client;

import com.romanov.model.request.Request;
import com.romanov.model.record.MedicalHistory;
import com.romanov.model.treatment.Treatment;
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    public Patient(String firstName, String lastName, int age,  String email, String phone) {
        super(firstName, lastName, age, email, phone);
    }

    public Patient(Patient patient)
    {
        super(patient.getFirstName(), patient.getLastName(), patient.getAge(), patient.getEmail(), patient.getPhone());
        this.setAddresses(new ArrayList<>());
    }

    Patient() {}

    public void addRequest(Request request)
    {
        this.requests.add(request);
        request.setOwner(this);
    }

    public void removeRequest(Request request)
    {
        this.requests.remove(request);
        request.setOwner(null);
    }
}
