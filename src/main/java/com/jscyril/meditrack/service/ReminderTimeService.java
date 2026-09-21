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
    private final CurrentUserService currentUserService;

    public ReminderTimeService(ReminderTimeRepository reminderTimeRepository, ReminderRepository reminderRepository,
                               CurrentUserService currentUserService) {
        this.reminderTimeRepository = reminderTimeRepository;
        this.reminderRepository = reminderRepository;
        this.currentUserService = currentUserService;
    }

    public ReminderTime create(ReminderTimeRequest request) {
        return reminderTimeRepository.save(toReminderTime(request));
    }

    public List<ReminderTime> findAll() {
        return reminderTimeRepository.findAllByReminder_User(currentUserService.get());
    }

    public Optional<ReminderTime> findById(Long id) {
        return reminderTimeRepository.findById(id)
                .filter(reminderTime -> reminderTime.getReminder().getUser().getUserId()
                        .equals(currentUserService.get().getUserId()));
    }

    public ReminderTime update(Long id, ReminderTimeRequest request) {
        return reminderTimeRepository.findById(id)
                .filter(reminderTime -> reminderTime.getReminder().getUser().getUserId()
                        .equals(currentUserService.get().getUserId()))
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
        if (findById(id).isEmpty()) {
            return false;
        }
        reminderTimeRepository.deleteById(id);
        return true;
    }

    private ReminderTime toReminderTime(ReminderTimeRequest request) {
        Reminder reminder = reminderRepository.findByReminderIdAndUser(request.reminderId(), currentUserService.get())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reminder not found"));
        return new ReminderTime(request.dosage(), reminder, request.timeOfDay());
    }
}
