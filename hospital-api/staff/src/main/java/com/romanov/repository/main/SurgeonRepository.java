package com.romanov.repository.main;

import com.romanov.model.staff.Surgeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SurgeonRepository extends JpaRepository<Surgeon, Long> {

    @Query("SELECT s FROM Surgeon s WHERE s.email = ?1")
    Surgeon findByEmail(String email);

}
