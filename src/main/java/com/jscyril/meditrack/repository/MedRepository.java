package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedRepository extends JpaRepository<Medicine, Long> {
}
