package com.cs.error;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ErrorHandler implements GraphQLErrorHandler {


    @Override
    public boolean errorsPresent(List<GraphQLError> errors) {
        return !CollectionUtils.isEmpty(errors);
    }

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors;
    }
}
