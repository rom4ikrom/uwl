package com.romanov.repository.main;

import com.romanov.model.staff.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}