package com.cs.service;

import com.cs.model.Tyre;
import com.cs.repository.TyreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TyreService {
    private final TyreRepository tyreRepository;

    public TyreService(final TyreRepository tyreRepository) {
        this.tyreRepository = tyreRepository;
    }

    @Transactional
    public Tyre createTyre(Tyre tyre) {
        return this.tyreRepository.save(tyre);
    }

    public List<Tyre> getTyres(long vehicleId) {
        return tyreRepository.findByVehicleId(vehicleId);
    }
}
