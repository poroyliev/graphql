package com.cs.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cs.model.Tyre;
import com.cs.service.TyreService;
import org.springframework.stereotype.Component;

@Component
public class TyreMutation implements GraphQLMutationResolver {
    private final TyreService vehicleService;

    public TyreMutation(TyreService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Tyre createTyre(Tyre vehicleId) {
        return this.vehicleService.createTyre(vehicleId);
    }

    public Tyre flatTyre() {
       throw new IllegalArgumentException();
    }
}
