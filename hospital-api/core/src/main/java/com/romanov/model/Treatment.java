package com.romanov.model;

import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Surgeon;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
//@Entity
public class Treatment {

    private String id;

    private List<Surgeon> surgeons;
    private List<Consultant> consultants;

    private List<Medicine> medicines;

    private Double servicePrice;
    private Double medicinePrice;
    private Double totalPrice;

    private int duration;

    private TreatmentStatus status;

    private Treatment() {}

    public enum TreatmentStatus
    {
        ACTIVE, CANCELED, PENDING;
    }
}
