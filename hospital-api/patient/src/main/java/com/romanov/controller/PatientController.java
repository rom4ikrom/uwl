package com.romanov.controller;

import com.romanov.model.Test;
import com.romanov.model.staff.Address;
import com.romanov.model.staff.Patient;
import com.romanov.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService)
    {
        this.patientService = patientService;
    }

    @PostMapping(consumes = "application/json")
    public Patient savePatient(@RequestBody Patient patient)
    {
        return patientService.savePatient(patient);
    }

}
