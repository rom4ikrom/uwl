package com.romanov.repository.main;

import com.romanov.model.record.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

//    @Query("SELECT r FROM medical_record r WHERE r.history_id = ?1")
//    List<MedicalRecord> listMedicalRecordsByHistoryId(long historyId);

}
