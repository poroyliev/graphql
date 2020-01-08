package com.cs.repository;

import com.cs.model.Tyre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TyreRepository extends JpaRepository<Tyre, Long> {
    List<Tyre> findByVehicleId(Long vehicleId);
}
