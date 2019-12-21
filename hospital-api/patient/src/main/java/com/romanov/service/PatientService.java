package com.romanov.service;

import com.romanov.model.staff.Address;
import com.romanov.model.staff.Patient;
import com.romanov.repository.main.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Patient updatePatient(Patient oldPatient, Patient updatedPatient)
    {
        long id = oldPatient.getId();
        updatedPatient.setId(id);

        return patientRepository.save(updatedPatient);
    }

    public Patient getPatient(long id)
    {
        return patientRepository.findById(id).orElse(null);
    }

}
