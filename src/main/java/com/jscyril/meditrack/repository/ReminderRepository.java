package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.Reminder;
import com.jscyril.meditrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate date, LocalDate sameDate);
    List<Reminder> findByUserAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            User user, LocalDate date, LocalDate sameDate);
    List<Reminder> findAllByUser(User user);
    Optional<Reminder> findByReminderIdAndUser(Long id, User user);
}
