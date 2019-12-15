package com.romanov.model;

import com.romanov.model.staff.Practitioner;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalRecord {

    private String id;

    private Date startDate;
    private Date endDate;

    private Practitioner practitioner;
}
