package com.romanov.repository.main;

import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {

    @Query("SELECT p FROM Practitioner p WHERE p.email = ?1")
    Practitioner findByEmail(String email);

}
