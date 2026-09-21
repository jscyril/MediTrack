package com.jscyril.meditrack.service;

import com.jscyril.meditrack.model.Reminder;
import com.jscyril.meditrack.model.ReminderTime;
import com.jscyril.meditrack.model.ReminderTimeRequest;
import com.jscyril.meditrack.repository.ReminderRepository;
import com.jscyril.meditrack.repository.ReminderTimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderTimeService {
    private final ReminderTimeRepository reminderTimeRepository;
    private final ReminderRepository reminderRepository;

    public ReminderTimeService(ReminderTimeRepository reminderTimeRepository, ReminderRepository reminderRepository) {
        this.reminderTimeRepository = reminderTimeRepository;
        this.reminderRepository = reminderRepository;
    }

    public ReminderTime create(ReminderTimeRequest request) {
        return reminderTimeRepository.save(toReminderTime(request));
    }

    public List<ReminderTime> findAll() {
        return reminderTimeRepository.findAll();
    }

    public Optional<ReminderTime> findById(Long id) {
        return reminderTimeRepository.findById(id);
    }

    public ReminderTime update(Long id, ReminderTimeRequest request) {
        return reminderTimeRepository.findById(id)
                .map(reminderTime -> {
                    ReminderTime updated = toReminderTime(request);
                    reminderTime.setDosage(updated.getDosage());
                    reminderTime.setTimeOfDay(updated.getTimeOfDay());
                    reminderTime.setReminder(updated.getReminder());
                    return reminderTimeRepository.save(reminderTime);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (!reminderTimeRepository.existsById(id)) {
            return false;
        }
        reminderTimeRepository.deleteById(id);
        return true;
    }

    private ReminderTime toReminderTime(ReminderTimeRequest request) {
        Reminder reminder = reminderRepository.findById(request.reminderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reminder not found"));
        return new ReminderTime(request.dosage(), reminder, request.timeOfDay());
    }
}
