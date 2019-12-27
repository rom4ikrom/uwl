package com.romanov.model.request;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseHospitalService {

    Appointment() {}

}
