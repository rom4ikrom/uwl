package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.service.AdminPatientService;
import com.romanov.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {

    private AdminPatientService adminPatientService;
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService,
                             AdminPatientService adminPatientService)
    {
        this.adminPatientService = adminPatientService;
        this.patientService = patientService;
    }

    @PostMapping("/request/{patient-id}")
    public List<Request> createRequest(@PathVariable("patient-id") long patientId,
                                       @Valid @RequestBody Request request) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(patientId);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return patientService.createRequest(patient, request);
    }

}
