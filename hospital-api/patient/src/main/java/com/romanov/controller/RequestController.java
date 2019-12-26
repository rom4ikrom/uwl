package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.service.AdminPatientService;
import com.romanov.service.PatientService;
import com.romanov.service.RequestService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/request")
public class RequestController {

    private RequestService requestService;
    private AdminPatientService adminPatientService;

    @Autowired
    public RequestController(RequestService requestService,
                             AdminPatientService adminPatientService)
    {
        this.requestService = requestService;
        this.adminPatientService = adminPatientService;
    }

    @PostMapping("/{patient-id}")
    public Patient createRequest(@PathVariable("patient-id") long patientId,
                                       @Valid @RequestBody Request request) throws NotFoundException
    {
        Patient patient = adminPatientService.getPatient(patientId);

        if(patient == null)
        {
            throw new NotFoundException(ExceptionCode.PATIENT_NOT_FOUND, "patient not found!");
        }

        return requestService.createRequest(patient, request);

    }

}
