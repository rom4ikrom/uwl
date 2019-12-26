package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.client.Patient;
import com.romanov.service.PatientService;
import com.romanov.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/patient")
public class AdminPatientController {

    private PatientService patientService;
    private PatientValidator patientValidator;

    @InitBinder("patient")
    public void initBinderPatient(WebDataBinder webDataBinder)
    {
        webDataBinder.setValidator(patientValidator);
    }

    @Autowired
    public AdminPatientController(PatientService patientService, PatientValidator patientValidator)
    {
        this.patientService = patientService;
        this.patientValidator = patientValidator;
    }

    @PostMapping(consumes = "application/json")
    public Patient savePatient(@Valid @RequestBody Patient patient)
    {
        return patientService.savePatient(patient);
    }

    @PutMapping(value = "/{patient-id}", consumes = "application/json")
    public Patient updatePatient(@PathVariable("patient-id") long id, @Valid @RequestBody Patient updatedPatient) throws NotFoundException
    {
        Patient oldPatient = patientService.getPatient(id);

        if(oldPatient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return patientService.updatePatient(oldPatient, updatedPatient);
    }

    @GetMapping(value = "/{patient-id}")
    public Patient getPatient(@PathVariable("patient-id") long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return patient;
    }

    @DeleteMapping(value = "/{patient-id}")
    public void deletePatient(@PathVariable("patient-id") long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        patientService.deletePatient(patient);
    }
}
