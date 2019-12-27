package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.config.exception.UnprocessableException;
import com.romanov.model.record.MedicalRecord;
import com.romanov.model.request.BaseHospitalService;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.treatment.Treatment;
import com.romanov.service.ProcessingService;
import com.romanov.service.RequestService;
import com.romanov.service.StaffService;
import com.romanov.service.TreatmentService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProcessingController {

    private ProcessingService processingService;
    private RequestService requestService;
    private TreatmentService treatmentService;
    private StaffService staffService;

    @Autowired
    public ProcessingController(ProcessingService processingService,
                                RequestService requestService,
                                TreatmentService treatmentService,
                                StaffService staffService)
    {
        this.processingService = processingService;
        this.requestService = requestService;
        this.treatmentService = treatmentService;
        this.staffService = staffService;
    }

    @GetMapping("/view/requests")
    public List<Request> getRequest(@RequestParam("request_status") RequestStatus requestStatus)
    {
        return processingService.getRequestByStatus(requestStatus);
    }

    @PostMapping("/confirm/request")
    public Request confirmRequest(@RequestParam("request_id") long requestId,
                                  @RequestParam("practitioner_id") long practitionerId) throws NotFoundException, UnprocessableException
    {
        Request request = requestService.getRequest(requestId);

        if(request == null)
        {
            throw new NotFoundException(ExceptionCode.REQUEST_NOT_FOUND, "request not found!");
        }

        if(request.getRequestStatus().equals(RequestStatus.APPROVED))
        {
            throw new UnprocessableException(ExceptionCode.INVALID_REQUEST, "request was already approved!");
        }

        Practitioner practitioner = staffService.getPractitioner(practitionerId);

        if(practitioner == null)
        {
            throw new NotFoundException(ExceptionCode.PRACTITIONER_NOT_FOUND, "practitioner not found!");
        }

        return processingService.confirmRequest(practitioner, request);
    }

    @PostMapping("create/treatment")
    public MedicalRecord createTreatment(@RequestParam("request_id") long requestId,
                                         @RequestParam("practitioner_id") long practitionerId,
                                         @RequestBody Treatment treatment) throws NotFoundException, UnprocessableException
    {
        Request request = requestService.getRequest(requestId);

        if(request == null)
        {
            throw new NotFoundException(ExceptionCode.REQUEST_NOT_FOUND, "request not found!");
        }

        if(request.getRequestStatus().equals(RequestStatus.PENDING))
        {
            throw new UnprocessableException(ExceptionCode.INVALID_REQUEST, "request should be approved first!");
        }

        Practitioner practitioner = staffService.getPractitioner(practitionerId);

        if(practitioner == null)
        {
            throw new NotFoundException(ExceptionCode.PRACTITIONER_NOT_FOUND, "practitioner not found!");
        }

        return processingService.createTreatmentMedicalRecord(request, practitioner, treatment);
    }


}
