package com.cs.grapql.repository;

import com.cs.grapql.model.Tyre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TyreRepository extends JpaRepository<Tyre, Long> {
    List<Tyre> findByVehicleId(Integer vehicleId);
}
