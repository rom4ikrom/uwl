package com.romanov.repository.main;

import com.romanov.model.client.Patient;
import com.romanov.model.staff.Surgeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.email = ?1")
    Patient findByEmail(String email);

}