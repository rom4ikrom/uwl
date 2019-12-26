package com.romanov.feign;

import com.romanov.model.record.MedicalRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("patient")
public interface PatientFeignClient {

    @PostMapping("/admin/history/{history-id}")
    MedicalRecord addMedicalRecord(@PathVariable("history-id") long historyId, @RequestBody MedicalRecord medicalRecord);

}
