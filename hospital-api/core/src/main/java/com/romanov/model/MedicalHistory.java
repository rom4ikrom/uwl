package com.romanov.model;

import com.romanov.model.client.Patient;
import lombok.Data;

import java.util.List;

@Data
//@Entity
public class MedicalHistory {

    private String id;

    private Patient patient;

    private List<MedicalRecord> medicalRecords;

    private MedicalHistory() {}
}
