package com.romanov.service;

import com.romanov.model.client.Patient;
import com.romanov.repository.main.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPatientService {

    private PatientRepository patientRepository;

    @Autowired
    public AdminPatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    public Patient savePatient(Patient patient)
    {
        Patient persistPatient = new Patient(patient);
        persistPatient.addAddresses(patient.getAddresses());
        return patientRepository.save(persistPatient);
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

    public void deletePatient(Patient patient)
    {
        patientRepository.delete(patient);
    }

}
