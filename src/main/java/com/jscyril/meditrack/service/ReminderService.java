package com.jscyril.meditrack.service;

import com.jscyril.meditrack.model.Medicine;
import com.jscyril.meditrack.model.Reminder;
import com.jscyril.meditrack.model.ReminderRequest;
import com.jscyril.meditrack.model.User;
import com.jscyril.meditrack.repository.MedRepository;
import com.jscyril.meditrack.repository.ReminderRepository;
import com.jscyril.meditrack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;
    private final MedRepository medRepository;
    private final CurrentUserService currentUserService;

    public ReminderService(ReminderRepository reminderRepository, UserRepository userRepository,
                           MedRepository medRepository, CurrentUserService currentUserService) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
        this.medRepository = medRepository;
        this.currentUserService = currentUserService;
    }

    public Reminder create(ReminderRequest request) {
        return reminderRepository.save(toReminder(request, currentUserService.get()));
    }

    public List<Reminder> findAll() {
        return reminderRepository.findAllByUser(currentUserService.get());
    }

    public Optional<Reminder> findById(Long id) {
        return reminderRepository.findByReminderIdAndUser(id, currentUserService.get());
    }

    public List<Reminder> findDueOn(LocalDate date) {
        return reminderRepository.findByUserAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                currentUserService.get(), date, date);
    }

    public Reminder update(Long id, ReminderRequest request) {
        User user = currentUserService.get();
        return reminderRepository.findByReminderIdAndUser(id, user)
                .map(reminder -> {
                    Reminder updated = toReminder(request, user);
                    reminder.setReminderDescription(updated.getReminderDescription());
                    reminder.setStartDate(updated.getStartDate());
                    reminder.setEndDate(updated.getEndDate());
                    reminder.setUser(updated.getUser());
                    reminder.setMedicine(updated.getMedicine());
                    return reminderRepository.save(reminder);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (findById(id).isEmpty()) {
            return false;
        }
        reminderRepository.deleteById(id);
        return true;
    }

    private Reminder toReminder(ReminderRequest request, User user) {
        Medicine medicine = medRepository.findById(request.medicineId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found"));
        return new Reminder(request.description(), user, medicine, request.startDate(), request.endDate());
    }
}
