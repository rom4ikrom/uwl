package com.romanov.repository.main;

import com.romanov.model.staff.Surgeon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeonRepository extends JpaRepository<Surgeon, Long> {
}
