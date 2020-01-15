package com.cs.grapql.error;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class CSGraphQLError extends RuntimeException implements GraphQLError {

    public CSGraphQLError(String message) {
        super(message);
    }

    public CSGraphQLError(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
