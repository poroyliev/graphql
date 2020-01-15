package com.cs.grapql.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cs.grapql.model.Tyre;
import com.cs.grapql.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
public class TyreMutation implements GraphQLMutationResolver {
    private final TyreService vehicleService;

    public Tyre createTyre(@Valid Tyre tyre) {
        return this.vehicleService.createTyre(tyre);
    }

    public Tyre createTyreLastArg(String str, @Valid Tyre tyre) {
        return this.vehicleService.createTyre(tyre);
    }

    public Tyre createTyreOnlyArg(@Valid Tyre tyre) {
        return this.vehicleService.createTyre(tyre);
    }

    public Tyre createTyreFirstArg(@Valid Tyre tyre, String str) {
        return this.vehicleService.createTyre(tyre);
    }

    public Tyre flatTyre() {
       throw new IllegalArgumentException();
    }


}
