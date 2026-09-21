package com.jscyril.meditrack.repository;

import com.jscyril.meditrack.model.Stock;
import com.jscyril.meditrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByUser(User user);
    Optional<Stock> findByStockIdAndUser(Long id, User user);
}
