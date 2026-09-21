package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
