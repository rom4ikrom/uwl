package com.romanov.service;

import com.romanov.model.treatment.Treatment;
import com.romanov.repository.main.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    private TreatmentRepository treatmentRepository;
    private StaffService staffService;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository,
                            StaffService staffService)
    {
        this.treatmentRepository = treatmentRepository;
        this.staffService = staffService;
    }

    public Treatment createTreatment(Treatment treatment)
    {


        return treatment;

    }

}
