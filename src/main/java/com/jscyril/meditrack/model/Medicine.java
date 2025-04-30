package com.jscyril.meditrack.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicineId")
    private Long medicineId;
    private String medicineName;
    private String description;


    public Medicine() {}

    public Medicine(String medicine_name, String description) {
        this.medicineName = medicine_name;
        this.description = description;
    }

    public Long getMedicineId(){
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName(){
        return medicineName;
    }

    public void setMedicineName(String medicine_name) {
        this.medicineName = medicine_name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
