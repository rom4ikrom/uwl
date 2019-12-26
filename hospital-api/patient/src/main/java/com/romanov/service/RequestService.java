package com.romanov.service;

import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.repository.main.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private PatientRepository patientRepository;

    @Autowired
    public RequestService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    public Patient createRequest(Patient patient, Request request)
    {
        patient.addRequest(request);
        return patientRepository.save(patient);
    }

}
