package com.jscyril.meditrack.service;

import com.jscyril.meditrack.model.Medicine;
import com.jscyril.meditrack.repository.MedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    private final MedRepository medRepository;

    public MedicineService(MedRepository medRepository) {
        this.medRepository = medRepository;
    }

    public Medicine create(Medicine medicine) {
        return medRepository.save(medicine);
    }

    public List<Medicine> findAll() {
        return medRepository.findAll();
    }

    public Optional<Medicine> findById(Long id) {
        return medRepository.findById(id);
    }

    public Medicine update(Long id, Medicine update) {
        return medRepository.findById(id)
                .map(medicine -> {
                    medicine.setMedicineName(update.getMedicineName());
                    medicine.setDescription(update.getDescription());
                    return medRepository.save(medicine);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (!medRepository.existsById(id)) {
            return false;
        }
        medRepository.deleteById(id);
        return true;
    }
}
