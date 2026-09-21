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
    private final CurrentUserService currentUserService;

    public HealthLogService(HealthLogRepository healthLogRepository, UserRepository userRepository,
                            CurrentUserService currentUserService) {
        this.healthLogRepository = healthLogRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    public HealthLog create(HealthLogRequest request) {
        return healthLogRepository.save(toHealthLog(request, currentUserService.get()));
    }

    public List<HealthLog> findAll() {
        return healthLogRepository.findAllByUser(currentUserService.get());
    }

    public Optional<HealthLog> findById(Long id) {
        return healthLogRepository.findByIdAndUser(id, currentUserService.get());
    }

    public HealthLog update(Long id, HealthLogRequest request) {
        User user = currentUserService.get();
        return healthLogRepository.findByIdAndUser(id, user)
                .map(healthLog -> {
                    HealthLog updated = toHealthLog(request, user);
                    healthLog.setLogTime(updated.getLogTime());
                    healthLog.setLogType(updated.getLogType());
                    healthLog.setValue(updated.getValue());
                    healthLog.setUser(updated.getUser());
                    return healthLogRepository.save(healthLog);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (findById(id).isEmpty()) {
            return false;
        }
        healthLogRepository.deleteById(id);
        return true;
    }

    private HealthLog toHealthLog(HealthLogRequest request, User user) {
        return new HealthLog(request.logTime(), request.logType(), request.value(), user);
    }
}
