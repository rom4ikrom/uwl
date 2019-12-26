package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.record.MedicalHistory;
import com.romanov.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/history")
public class HistoryController {

    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    @GetMapping("/view")
    public MedicalHistory getMedicalHistory(@RequestParam("history_id") long historyId) throws NotFoundException
    {
        MedicalHistory medicalHistory = historyService.getMedicalHistory(historyId);

        if(medicalHistory == null)
        {
            throw new NotFoundException(ExceptionCode.HISTORY_NOT_FOUND, "history not found!");
        }

        return medicalHistory;
    }
}
