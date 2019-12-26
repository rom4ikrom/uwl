package com.romanov.controller;

import com.romanov.config.exception.ExceptionCode;
import com.romanov.config.exception.NotFoundException;
import com.romanov.model.record.MedicalHistory;
import com.romanov.model.record.MedicalRecord;
import com.romanov.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {

    private HistoryService historyService;

    @Autowired
    public AdminHistoryController(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    @PostMapping(value = "/{history-id}")
    public MedicalHistory addMedicalRecord(@PathVariable("history-id") long historyId,
                                           @RequestBody MedicalRecord medicalRecord) throws NotFoundException
    {
        MedicalHistory medicalHistory = historyService.getMedicalHistory(historyId);

        if(medicalHistory == null)
        {
            throw new NotFoundException(ExceptionCode.HISTORY_NOT_FOUND, "history not found!");
        }

        return historyService.addMedicalRecord(medicalHistory, medicalRecord);
    }

}
