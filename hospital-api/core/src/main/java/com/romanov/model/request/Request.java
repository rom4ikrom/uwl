package com.romanov.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.client.Patient;
import com.romanov.model.staff.Practitioner;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "request_type")
    private RequestType requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Patient owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Practitioner manager;

    @OneToOne(mappedBy = "request")
    @JsonIgnore
    private BaseHospitalService baseHospitalService;

    private Request() {};

    public boolean isTreatment()
    {
        return RequestType.TREATMENT.equals(this.requestType);
    }

    public boolean isAppointment()
    {
        return RequestType.APPOINTMENT.equals(this.requestType);
    }

    public boolean isAnalysis()
    {
        return RequestType.ANALYSIS.equals(this.requestType);
    }

}