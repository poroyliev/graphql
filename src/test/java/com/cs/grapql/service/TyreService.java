package com.cs.grapql.service;

import com.cs.grapql.model.Tyre;
import com.cs.grapql.repository.TyreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TyreService {
    private final TyreRepository tyreRepository;

    public TyreService(final TyreRepository tyreRepository) {
        this.tyreRepository = tyreRepository;
    }

    @Transactional
    public Tyre createTyre(Tyre tyre) {
        return this.tyreRepository.save(tyre);
    }

    public List<Tyre> getTyres(Integer vehicleId) {
        return tyreRepository.findByVehicleId(vehicleId);
    }
}
