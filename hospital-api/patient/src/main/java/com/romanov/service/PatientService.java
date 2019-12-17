package com.romanov.service;

import com.romanov.model.staff.Patient;
import com.romanov.repository.main.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    public Patient savePatient(Patient patient)
    {
        return patientRepository.save(patient);
    }

}
