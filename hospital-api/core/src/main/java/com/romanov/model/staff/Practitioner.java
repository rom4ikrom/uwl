package com.romanov.model.staff;

import com.romanov.model.request.Request;
import com.romanov.model.record.MedicalRecord;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.PersonRole;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    public Practitioner(Practitioner practitioner)
    {
        super(practitioner);
    }

    Practitioner() {}

    @Override
    public String toString()
    {
        return "Practitioner: {}";
    }

    public void addMedicalRecord(MedicalRecord medicalRecord)
    {
        this.medicalRecords.add(medicalRecord);
        medicalRecord.setPractitioner(this);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord)
    {
        this.medicalRecords.remove(medicalRecord);
        medicalRecord.setPractitioner(null);
    }

    public void addTreatment(Treatment treatment)
    {
        this.treatments.add(treatment);
        treatment.setMaker(this);
    }

    public void removeTreatment(Treatment treatment)
    {
        this.treatments.remove(treatment);
        treatment.setMaker(null);
    }

    public void addRequest(Request request)
    {
        this.requests.add(request);
        request.setManager(this);
    }

    public void removeRequest(Request request)
    {
        this.requests.remove(request);
        request.setManager(null);
    }

}
