package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.HealthLog;
import com.jscyril.meditrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {
    List<HealthLog> findAllByUser(User user);
    Optional<HealthLog> findByIdAndUser(Long id, User user);
}
