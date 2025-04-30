package com.jscyril.meditrack.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;
    private int quantity;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public Stock(){}

    public Stock(int quantity, LocalDate expiryDate, User user, Medicine medicine){
        this.expiryDate = expiryDate;
        this.user = user;
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public Long getStockId(){
        return stockId;
    }

    public void setStockId(Long stockId){
        this.stockId = stockId;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public LocalDate getExpiryDate(){
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Medicine getMedicine(){
        return medicine;
    }
    public void setMedicine(Medicine medicine){
        this.medicine = medicine;
    }
}
