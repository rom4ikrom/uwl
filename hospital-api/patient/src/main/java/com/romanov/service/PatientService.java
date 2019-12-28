package com.romanov.service;

import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.repository.main.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }


}
