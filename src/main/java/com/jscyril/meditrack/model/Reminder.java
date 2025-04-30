package com.jscyril.meditrack.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private Long reminderId;
    @Column(name = "reminder_description")
    private String reminderDescription;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public Reminder() {
    }

    public Reminder(String reminderDescription, User user, Medicine medicine, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.medicine = medicine;
        this.reminderDescription = reminderDescription;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReminderDescription(){
        return reminderDescription;
    }
    public void setReminderDescription(String reminderDescription){
        this.reminderDescription = reminderDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
