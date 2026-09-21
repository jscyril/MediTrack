package com.jscyril.meditrack.controller;

import com.jscyril.meditrack.model.HealthLog;
import com.jscyril.meditrack.model.HealthLogRequest;
import com.jscyril.meditrack.service.HealthLogService;
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
@RequestMapping("/api/health-logs")
public class HealthLogController {
    private final HealthLogService healthLogService;

    public HealthLogController(HealthLogService healthLogService) {
        this.healthLogService = healthLogService;
    }

    @PostMapping
    public HealthLog create(@Valid @RequestBody HealthLogRequest request) {
        return healthLogService.create(request);
    }

    @GetMapping
    public List<HealthLog> findAll() {
        return healthLogService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthLog> findById(@PathVariable Long id) {
        return healthLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthLog> update(@PathVariable Long id,
                                            @Valid @RequestBody HealthLogRequest request) {
        HealthLog updated = healthLogService.update(id, request);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return healthLogService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
