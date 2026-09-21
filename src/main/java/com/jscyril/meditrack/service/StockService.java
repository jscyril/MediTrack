package com.jscyril.meditrack.service;

import com.jscyril.meditrack.model.Medicine;
import com.jscyril.meditrack.model.Stock;
import com.jscyril.meditrack.model.StockRequest;
import com.jscyril.meditrack.model.User;
import com.jscyril.meditrack.repository.MedRepository;
import com.jscyril.meditrack.repository.StockRepository;
import com.jscyril.meditrack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final MedRepository medRepository;
    private final CurrentUserService currentUserService;

    public StockService(StockRepository stockRepository, UserRepository userRepository, MedRepository medRepository,
                        CurrentUserService currentUserService) {
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
        this.medRepository = medRepository;
        this.currentUserService = currentUserService;
    }

    public Stock create(StockRequest request) {
        return stockRepository.save(toStock(request, currentUserService.get()));
    }

    public List<Stock> findAll() {
        return stockRepository.findAllByUser(currentUserService.get());
    }

    public Optional<Stock> findById(Long id) {
        return stockRepository.findByStockIdAndUser(id, currentUserService.get());
    }

    public Stock update(Long id, StockRequest request) {
        User user = currentUserService.get();
        return stockRepository.findByStockIdAndUser(id, user)
                .map(stock -> {
                    Stock updated = toStock(request, user);
                    stock.setQuantity(updated.getQuantity());
                    stock.setExpiryDate(updated.getExpiryDate());
                    stock.setUser(updated.getUser());
                    stock.setMedicine(updated.getMedicine());
                    return stockRepository.save(stock);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (findById(id).isEmpty()) {
            return false;
        }
        stockRepository.deleteById(id);
        return true;
    }

    private Stock toStock(StockRequest request, User user) {
        Medicine medicine = medRepository.findById(request.medicineId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found"));
        return new Stock(request.quantity(), request.expiryDate(), user, medicine);
    }
}
