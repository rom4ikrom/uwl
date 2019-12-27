package com.romanov.model.treatment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.UnprocessableException;
import com.romanov.model.client.Patient;
import com.romanov.model.record.MedicalRecord;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.util.Funcs;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "treatment")
public class Treatment {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Surgeon> surgeons = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Consultant> consultants = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Medicine> medicines = new ArrayList<>();

    private String recommendations;

    private Double totalPrice;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id")
    @JsonIgnore
    private Practitioner maker;

    @OneToOne(mappedBy = "treatment")
    @JsonIgnore
    private Patient takenBy;

    @Column(name = "treatment_status")
    private TreatmentStatus status;

    Treatment() {}

    public Treatment(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = TreatmentStatus.ACTIVE;
    }

    @Override
    public String toString()
    {
        return "MedicalHistory: {}";
    }

    public void setTotalPrice()
    {
        this.totalPrice = getTotalPrice();
    }

    public Double getTotalPrice()
    {
        return getMedicinePrice() + getServicePrice();
    }

    private Double getMedicinePrice()
    {
        return this.medicines.stream().mapToDouble(Medicine::getPrice).sum();
    }

    private Double getServicePrice()
    {
        long duration = Funcs.dateDiff(this.startDate, this.endDate);
        Double serviceSurgeonPrice = (double) (duration * this.surgeons.size() * 12);
        Double serviceConsultantPrice = (double) (duration * this.consultants.size() * 9);

        return serviceSurgeonPrice + serviceConsultantPrice;
    }

    public void addMedicine(Medicine medicine)
    {
        this.medicines.add(medicine);
        medicine.setTreatment(this);
    }

    public void addMedicines(List<Medicine> medicines)
    {
        for(Medicine medicine : medicines)
        {
            addMedicine(medicine);
        }
    }

    public void removeMedicine(Medicine medicine) throws UnprocessableException
    {
        if(this.medicines.size() == 0)
        {
            throw new UnprocessableException(ExceptionCode.INVALID_MEDICINE_NUMBER, "Number of surgeons can not be less than 0!");
        }

        this.medicines.remove(medicine);
        medicine.setTreatment(null);
    }

    public void addSurgeons(List<Surgeon> surgeons) throws UnprocessableException
    {
        for(Surgeon surgeon : surgeons)
        {
            addSurgeon(surgeon);
        }
    }

    public void addSurgeon(Surgeon surgeon) throws UnprocessableException
    {
        if(this.surgeons.size() == 5)
        {
            throw new UnprocessableException(ExceptionCode.INVALID_SURGEON_NUMBER, "Number of surgeons can not exceed 5!");
        }

        surgeons.add(surgeon);
        surgeon.setTreatment(this);
    }

    public void removeSurgeon(Surgeon surgeon) throws UnprocessableException
    {
        if(this.surgeons.size() == 0)
        {
            throw new UnprocessableException(ExceptionCode.INVALID_SURGEON_NUMBER, "Number of surgeons can not be less than 0!");
        }

        surgeons.remove(surgeon);
        surgeon.setTreatment(null);
    }

    public void addConsultants(List<Consultant> consultants) throws UnprocessableException
    {
        for(Consultant consultant : consultants)
        {
            addConsultant(consultant);
        }
    }

    public void addConsultant(Consultant consultant) throws UnprocessableException
    {
        if(this.consultants.size() == 2)
        {
            throw new UnprocessableException(ExceptionCode.INVALID_CONSULTANT_NUMBER, "Number of consultants can not exceed 2!");
        }

        consultants.add(consultant);
        consultant.setTreatment(this);
    }

    public void removeConsultant(Consultant consultant) throws UnprocessableException
    {
        if(this.consultants.size() == 0)
        {
            throw new UnprocessableException(ExceptionCode.INVALID_CONSULTANT_NUMBER, "Number of consultants can not be less than 0!");
        }

        consultants.remove(consultant);
        consultant.setTreatment(null);
    }



}
