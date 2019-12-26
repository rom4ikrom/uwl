package com.romanov.service;

import com.romanov.model.record.MedicalHistory;
import com.romanov.model.record.MedicalRecord;
import com.romanov.repository.main.MedicalHistoryRepository;
import com.romanov.repository.main.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private MedicalRecordRepository medicalRecordRepository;
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    public HistoryService(MedicalRecordRepository medicalRecordRepository,
                          MedicalHistoryRepository medicalHistoryRepository)
    {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public MedicalHistory addMedicalRecord(MedicalHistory medicalHistory, MedicalRecord medicalRecord)
    {
        medicalHistory.addMedicalRecord(medicalRecord);
        return medicalHistoryRepository.save(medicalHistory);
    }

    public MedicalHistory getMedicalHistory(long id)
    {
        return medicalHistoryRepository.findById(id).orElse(null);
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord)
    {
        return medicalRecordRepository.save(medicalRecord);
    }

}
