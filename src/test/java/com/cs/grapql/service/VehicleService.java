package com.cs.grapql.service;

import com.cs.grapql.model.Vehicle;
import com.cs.grapql.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository ;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository ;
    }

    @Transactional
    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));
        return this.vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(int count) {
        return this.vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public Optional<Vehicle> getVehicle(Long id) {
        return this.vehicleRepository.findById(id);
    }
}
