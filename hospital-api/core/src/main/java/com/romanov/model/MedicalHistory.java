package com.romanov.model;

import com.romanov.model.staff.Patient;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
//@Entity
public class MedicalHistory {

    private String id;

    private Patient patient;

    private List<MedicalRecord> medicalRecords;

    private MedicalHistory() {};
}
