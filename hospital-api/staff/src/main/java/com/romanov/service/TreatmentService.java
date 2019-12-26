package com.romanov.service;

import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.treatment.Treatment;
import com.romanov.repository.main.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    private TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository)
    {
        this.treatmentRepository = treatmentRepository;
    }

    public Treatment saveTreatment(Treatment treatment)
    {
        return treatmentRepository.save(treatment);
    }

}
