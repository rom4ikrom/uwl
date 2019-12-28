package com.romanov.service;

import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.model.staff.Practitioner;
import com.romanov.repository.main.PatientRepository;
import com.romanov.repository.main.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    private PatientRepository patientRepository;
    private RequestRepository requestRepository;

    @Autowired
    public RequestService(PatientRepository patientRepository,
                          RequestRepository requestRepository)
    {
        this.patientRepository = patientRepository;
        this.requestRepository = requestRepository;

    }

    public List<Request> createRequest(Patient patient, Request request)
    {
        request.setRequestStatus(RequestStatus.PENDING);
        patient.addRequest(request);
        request.setDate(new Date());

        return patientRepository.save(patient).getRequests();
    }

    public Request getRequest(long requestId)
    {
        return requestRepository.findById(requestId).orElse(null);
    }

    public List<Request> getRequestByStatus(RequestStatus requestStatus)
    {
        return requestRepository.findRequestsByStatus(requestStatus);
    }

    public Request confirmRequest(Practitioner practitioner, Request request)
    {
        request.setRequestStatus(RequestStatus.APPROVED);
        practitioner.addRequest(request);
        return requestRepository.save(request);
    }

}
