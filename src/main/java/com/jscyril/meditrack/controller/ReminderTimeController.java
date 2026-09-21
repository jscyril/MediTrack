package com.jscyril.meditrack.controller;

import com.jscyril.meditrack.model.ReminderTime;
import com.jscyril.meditrack.model.ReminderTimeRequest;
import com.jscyril.meditrack.service.ReminderTimeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reminder-times")
public class ReminderTimeController {
    private final ReminderTimeService reminderTimeService;

    public ReminderTimeController(ReminderTimeService reminderTimeService) {
        this.reminderTimeService = reminderTimeService;
    }

    @PostMapping
    public ReminderTime create(@Valid @RequestBody ReminderTimeRequest request) {
        return reminderTimeService.create(request);
    }

    @GetMapping
    public List<ReminderTime> findAll() {
        return reminderTimeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderTime> findById(@PathVariable Long id) {
        return reminderTimeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderTime> update(@PathVariable Long id,
                                               @Valid @RequestBody ReminderTimeRequest request) {
        ReminderTime updated = reminderTimeService.update(id, request);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return reminderTimeService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
