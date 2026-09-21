package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
