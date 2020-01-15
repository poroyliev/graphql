package com.cs.grapql.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.cs.grapql.model.Vehicle;
import com.cs.grapql.service.VehicleService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

    private VehicleService vehicleService;

    public VehicleQuery(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Vehicle> getVehicles(final int count) {
        return this.vehicleService.getAllVehicles(count);
    }

    public Optional<Vehicle> getVehicle(final Long id) {
        return this.vehicleService.getVehicle(id);
    }
}
