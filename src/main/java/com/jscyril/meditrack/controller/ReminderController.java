package com.jscyril.meditrack.controller;

import com.jscyril.meditrack.model.Reminder;
import com.jscyril.meditrack.model.ReminderRequest;
import com.jscyril.meditrack.service.ReminderService;
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
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public Reminder create(@Valid @RequestBody ReminderRequest request) {
        return reminderService.create(request);
    }

    @GetMapping
    public List<Reminder> findAll() {
        return reminderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> findById(@PathVariable Long id) {
        return reminderService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> update(@PathVariable Long id, @Valid @RequestBody ReminderRequest request) {
        Reminder updated = reminderService.update(id, request);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return reminderService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
