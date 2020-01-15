package com.cs.grapql.graphql;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.cs.grapql.model.Tyre;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SubscriptionQuery implements GraphQLSubscriptionResolver {

    public Flux<Tyre> hello(Long seconds, DataFetchingEnvironment environment) throws IOException {
        return Flux.interval(Duration.of(1, ChronoUnit.MILLIS))
                .take(3)
                .map(Long::intValue)
                .map(Tyre::new);
    }

}
