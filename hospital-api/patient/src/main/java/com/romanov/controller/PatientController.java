package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.client.Patient;
import com.romanov.model.record.MedicalHistory;
import com.romanov.model.request.Request;
import com.romanov.service.AdminPatientService;
import com.romanov.service.HistoryService;
import com.romanov.service.PatientService;
import com.romanov.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {

    private AdminPatientService adminPatientService;
    private PatientService patientService;
    private RequestService requestService;
    private HistoryService historyService;

    @Autowired
    public PatientController(PatientService patientService,
                             AdminPatientService adminPatientService,
                             RequestService requestService,
                             HistoryService historyService)
    {
        this.adminPatientService = adminPatientService;
        this.patientService = patientService;
        this.requestService = requestService;
        this.historyService = historyService;
    }

    @PostMapping("/request")
    public List<Request> createRequest(@RequestParam("patient_id") long patientId,
                                       @Valid @RequestBody Request request) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(patientId);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return requestService.createRequest(patient, request);
    }

    @GetMapping("/history/view")
    public MedicalHistory getMedicalHistory(@RequestParam("patient_id") long patientId) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(patientId);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return patient.getMedicalHistory();
    }

}
