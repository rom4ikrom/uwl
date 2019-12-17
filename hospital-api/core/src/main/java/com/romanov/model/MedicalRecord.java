package com.romanov.model;

import com.romanov.model.staff.Practitioner;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
//@Entity
public class MedicalRecord {

    private String id;

    private Date startDate;
    private Date endDate;

    private Practitioner practitioner;

    private MedicalRecord() {};
}
