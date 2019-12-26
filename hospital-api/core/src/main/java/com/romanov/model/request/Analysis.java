package com.romanov.model.request;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "analysis")
public class Analysis extends BaseHospitalService {

    private Analysis() { super(); }
}
