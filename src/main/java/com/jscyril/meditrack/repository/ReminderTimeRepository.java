package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.ReminderTime;
import com.jscyril.meditrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderTimeRepository extends JpaRepository<ReminderTime, Long> {
    List<ReminderTime> findAllByReminder_User(User user);
}
