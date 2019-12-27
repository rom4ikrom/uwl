package com.romanov.model.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.staff.Practitioner;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "medical_record")
public class MedicalRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    private String description;

    private MedicalRecordStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practitioner_id")
    @JsonIgnore
    private Practitioner practitioner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    @JsonIgnore
    private MedicalHistory medicalHistory;

    MedicalRecord() {};

    public MedicalRecord(MedicalRecordStatus status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "MedicalRecord: {}";
    }
}
