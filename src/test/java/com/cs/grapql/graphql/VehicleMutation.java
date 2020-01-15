package com.cs.grapql.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cs.grapql.model.Vehicle;
import com.cs.grapql.service.VehicleService;
import org.springframework.stereotype.Component;

@Component
public class VehicleMutation implements GraphQLMutationResolver {
    private final VehicleService vehicleService;

    public VehicleMutation(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}
