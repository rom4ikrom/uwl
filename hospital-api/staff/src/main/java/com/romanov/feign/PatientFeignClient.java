package com.romanov.feign;

import com.romanov.model.record.MedicalRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("patient")
public interface PatientFeignClient {

    @PostMapping(value = "/admin/history/{patient-id}", consumes = "application/json")
    MedicalRecord addMedicalRecord(@PathVariable("patient-id") long patientId, @RequestBody MedicalRecord medicalRecord);

}
