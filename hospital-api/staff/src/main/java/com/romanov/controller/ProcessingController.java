package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.config.exception.UnprocessableException;
import com.romanov.model.request.BaseHospitalService;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.model.treatment.Treatment;
import com.romanov.service.ProcessingService;
import com.romanov.service.RequestService;
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

    @Autowired
    public ProcessingController(ProcessingService processingService,
                                RequestService requestService,
                                TreatmentService treatmentService)
    {
        this.processingService = processingService;
        this.requestService = requestService;
        this.treatmentService = treatmentService;
    }

    @GetMapping("/view/requests")
    public List<Request> getRequest(@RequestParam("request_status") RequestStatus requestStatus)
    {
        return processingService.getRequestByStatus(requestStatus);
    }

    @PostMapping("/confirm/request/{request-id}")
    public Request confirmRequest(@PathVariable("request-id") long requestId) throws NotFoundException, UnprocessableException
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

        return processingService.confirmRequest(request);
    }

    @PostMapping("create/treatment/{request-id}")
    public Treatment createTreatment(@PathVariable("request-id") long requestId,
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

        return treatmentService.createTreatment(treatment);
    }


}
