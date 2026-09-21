package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.HealthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {
}
