package com.cs.grapql.error;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Slf4j
public class CSErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        if(!CollectionUtils.isEmpty(errors)) {
            Flux.fromIterable(errors)
                    .subscribe(error -> log.error("{}", error));
        }

        return errors;
    }

}
