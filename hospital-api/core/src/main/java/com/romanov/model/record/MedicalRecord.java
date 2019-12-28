package com.romanov.model.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestType;
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

    private RequestType type;

    @Column(name = "service_id")
    private long serviceId;

    @Column(name = "request_id")
    private long requestId;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "practitioner_id")
    @JsonIgnore
    private Practitioner practitioner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    @JsonIgnore
    private MedicalHistory medicalHistory;

    MedicalRecord() {};

    public MedicalRecord(RequestType type)
    {
        this.type = type;
        this.status = MedicalRecordStatus.ACTIVE;
    }

    @Override
    public String toString()
    {
        return "MedicalRecord: {}";
    }
}
