package com.cs.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.cs.model.Tyre;
import com.cs.service.TyreService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TyreQuery implements GraphQLQueryResolver {

    private TyreService vehicleService;

    public TyreQuery(TyreService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Tyre> getTyres(final int count) {
        return this.vehicleService.getTyres(count);
    }

}
