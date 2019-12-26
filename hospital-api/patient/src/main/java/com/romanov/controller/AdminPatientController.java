package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.client.Patient;
import com.romanov.service.AdminPatientService;
import com.romanov.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/patient")
public class AdminPatientController {

    private AdminPatientService adminPatientService;
    private PatientValidator patientValidator;

    @InitBinder("patient")
    public void initBinderPatient(WebDataBinder webDataBinder)
    {
        webDataBinder.setValidator(patientValidator);
    }

    @Autowired
    public AdminPatientController(AdminPatientService adminPatientService, PatientValidator patientValidator)
    {
        this.adminPatientService = adminPatientService;
        this.patientValidator = patientValidator;
    }

    @PostMapping(consumes = "application/json")
    public Patient savePatient(@Valid @RequestBody Patient patient)
    {
        return adminPatientService.savePatient(patient);
    }

    @PutMapping(value = "/{patient-id}", consumes = "application/json")
    public Patient updatePatient(@PathVariable("patient-id") long id, @Valid @RequestBody Patient updatedPatient) throws NotFoundException
    {
        Patient oldPatient = adminPatientService.getPatient(id);

        if(oldPatient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return adminPatientService.updatePatient(oldPatient, updatedPatient);
    }

    @GetMapping(value = "/{patient-id}")
    public Patient getPatient(@PathVariable("patient-id") long id) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(id);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return patient;
    }

    @DeleteMapping(value = "/{patient-id}")
    public void deletePatient(@PathVariable("patient-id") long id) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(id);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        adminPatientService.deletePatient(patient);
    }
}
