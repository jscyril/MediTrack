package com.jscyril.meditrack.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HealthLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime logTime;
    private String logType;
    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public HealthLog() {
    }

    public HealthLog(LocalDateTime logTime, String logType, String value, User user) {
        this.logTime = logTime;
        this.logType = logType;
        this.value = value;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
