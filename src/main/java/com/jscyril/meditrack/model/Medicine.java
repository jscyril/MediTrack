package com.jscyril.meditrack.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int stock;
    private String dosage;
    private int timesPerDay;
    private String instructions;

    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate;

    public Medicine() {}

    public Medicine(String name, int stock, String dosage, int timesPerDay, String instructions, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.stock = stock;
        this.dosage = dosage;
        this.timesPerDay = timesPerDay;
        this.instructions = instructions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public boolean isActive() {
        return endDate == null || endDate.isAfter(LocalDate.now());
    }
}
