package com.romanov.model.treatment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romanov.model.client.Patient;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Surgeon> surgeons = new ArrayList<>();

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Consultant> consultants = new ArrayList<>();

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Medicine> medicines = new ArrayList<>();

    private Double totalPrice;

    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id")
    @JsonIgnore
    private Practitioner maker;

    @OneToOne(mappedBy = "treatment")
    @JsonIgnore
    private Patient takenBy;

    @Column(name = "treatment_status")
    private TreatmentStatus status;

    private Treatment() {}

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
        Double serviceSurgeonPrice = (double) (this.duration * this.surgeons.size() * 12);
        Double serviceConsultantPrice = (double) (this.duration * this.consultants.size() * 9);

        return serviceSurgeonPrice + serviceConsultantPrice;
    }

}
