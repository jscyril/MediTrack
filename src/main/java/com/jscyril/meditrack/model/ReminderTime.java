package com.jscyril.meditrack.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class ReminderTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_time_id")
    private Long reminderTimeId;
    private int dosage;
    @Column(name = "time_of_day")
    private LocalTime timeOfDay;

    @ManyToOne
    @JoinColumn(name = "reminder_id")
    private Reminder reminder;

    public ReminderTime(){}

    public ReminderTime(int dosage, Reminder reminder, LocalTime timeOfDay){
        this.dosage = dosage;
        this.timeOfDay = timeOfDay;
        this.reminder = reminder;
    }
    public Long getReminderTimeId(){
        return reminderTimeId;
    }
    public void setReminderTimeId(Long reminderTimeId){
        this.reminderTimeId = reminderTimeId;
    }

    public int getDosage(){
        return dosage;
    }
    public void setDosage(int dosage){
        this.dosage = dosage;
    }
    public LocalTime getTimeOfDay(){
        return timeOfDay;
    }
    public void setTimeOfDay(LocalTime timeOfDay){
        this.timeOfDay = timeOfDay;
    }

    public Reminder getReminder(){
        return reminder;
    }
    public void setReminder(Reminder reminder){
        this.reminder = reminder;
    }


}
