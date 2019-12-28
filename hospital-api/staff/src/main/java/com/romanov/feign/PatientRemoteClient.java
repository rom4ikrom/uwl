package com.romanov.feign;

import com.romanov.model.record.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientRemoteClient {

    private PatientFeignClient patientFeignClient;

    @Autowired
    private PatientRemoteClient(PatientFeignClient patientFeignClient)
    {
        this.patientFeignClient = patientFeignClient;
    }

    public MedicalRecord addMedicalRecord(long patientId, MedicalRecord medicalRecord)
    {
        return patientFeignClient.addMedicalRecord(patientId, medicalRecord);
    }

}
