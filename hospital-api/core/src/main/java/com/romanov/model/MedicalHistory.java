package com.romanov.model;

import com.romanov.model.staff.Client;
import lombok.Data;

import java.util.List;

@Data
public class MedicalHistory {

    private String id;

    private Client client;

    private List<MedicalRecord> medicalRecords;
}
