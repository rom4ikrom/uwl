package com.romanov.service;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.config.exception.UnprocessableException;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.repository.main.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository)
    {
        this.requestRepository = requestRepository;
    }

    public Request getRequest(long requestId)
    {
        return requestRepository.findById(requestId).orElse(null);
    }
}
