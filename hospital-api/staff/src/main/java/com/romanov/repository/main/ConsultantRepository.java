package com.romanov.repository.main;

import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    @Query("SELECT c FROM Consultant c WHERE c.email = ?1")
    Consultant findByEmail(String email);

}

