package com.romanov.service;

import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.model.treatment.Treatment;
import com.romanov.repository.main.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingService {

    private RequestRepository requestRepository;

    @Autowired
    public ProcessingService(RequestRepository requestRepository)
    {
        this.requestRepository = requestRepository;
    }

    public List<Request> getRequestByStatus(RequestStatus requestStatus)
    {
        return requestRepository.findRequestsByStatus(requestStatus);
    }

    public Request confirmRequest(Request request)
    {
        request.setRequestStatus(RequestStatus.APPROVED);
        return requestRepository.save(request);
    }

    public Treatment createTreatment(Treatment treatment)
    {
        return treatment;
    }

}
