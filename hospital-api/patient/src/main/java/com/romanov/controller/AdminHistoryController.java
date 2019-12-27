package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.client.Patient;
import com.romanov.model.record.MedicalHistory;
import com.romanov.model.record.MedicalRecord;
import com.romanov.service.AdminPatientService;
import com.romanov.service.HistoryService;
import com.romanov.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {

    private HistoryService historyService;
    private AdminPatientService adminPatientService;

    @Autowired
    public AdminHistoryController(HistoryService historyService,
                                  AdminPatientService adminPatientService)
    {
        this.historyService = historyService;
        this.adminPatientService = adminPatientService;
    }

    @PostMapping(value = "/{patient-id}")
    public MedicalHistory addMedicalRecord(@PathVariable("patient-id") long patientId,
                                           @RequestBody MedicalRecord medicalRecord) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(patientId);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return historyService.addMedicalRecord(patient.getMedicalHistory(), medicalRecord);
    }

}
