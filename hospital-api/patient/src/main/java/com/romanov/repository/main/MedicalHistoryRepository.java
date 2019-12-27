package com.romanov.repository.main;

import com.romanov.model.record.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

}
