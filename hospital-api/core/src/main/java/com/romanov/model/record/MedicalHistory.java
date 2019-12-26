package com.romanov.model.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.client.Patient;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "medical_history")
public class MedicalHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "medicalHistory")
    @JsonIgnore
    private Patient patient;

    @OneToMany(mappedBy = "medicalHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public MedicalHistory() {}

    public void addMedicalRecord(MedicalRecord medicalRecord)
    {
        this.medicalRecords.add(medicalRecord);
        medicalRecord.setMedicalHistory(this);
    }

    public void removeMedicalRecords(MedicalRecord medicalRecord)
    {
        this.medicalRecords.remove(medicalRecord);
        medicalRecord.setMedicalHistory(null);
    }
}
