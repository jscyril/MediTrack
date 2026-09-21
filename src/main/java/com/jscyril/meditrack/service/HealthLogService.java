package com.jscyril.meditrack.service;

import com.jscyril.meditrack.model.HealthLog;
import com.jscyril.meditrack.model.HealthLogRequest;
import com.jscyril.meditrack.model.User;
import com.jscyril.meditrack.repository.HealthLogRepository;
import com.jscyril.meditrack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HealthLogService {
    private final HealthLogRepository healthLogRepository;
    private final UserRepository userRepository;

    public HealthLogService(HealthLogRepository healthLogRepository, UserRepository userRepository) {
        this.healthLogRepository = healthLogRepository;
        this.userRepository = userRepository;
    }

    public HealthLog create(HealthLogRequest request) {
        return healthLogRepository.save(toHealthLog(request));
    }

    public List<HealthLog> findAll() {
        return healthLogRepository.findAll();
    }

    public Optional<HealthLog> findById(Long id) {
        return healthLogRepository.findById(id);
    }

    public HealthLog update(Long id, HealthLogRequest request) {
        return healthLogRepository.findById(id)
                .map(healthLog -> {
                    HealthLog updated = toHealthLog(request);
                    healthLog.setLogTime(updated.getLogTime());
                    healthLog.setLogType(updated.getLogType());
                    healthLog.setValue(updated.getValue());
                    healthLog.setUser(updated.getUser());
                    return healthLogRepository.save(healthLog);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (!healthLogRepository.existsById(id)) {
            return false;
        }
        healthLogRepository.deleteById(id);
        return true;
    }

    private HealthLog toHealthLog(HealthLogRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new HealthLog(request.logTime(), request.logType(), request.value(), user);
    }
}
