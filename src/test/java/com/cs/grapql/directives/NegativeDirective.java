package com.cs.grapql.directives;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.stereotype.Component;

@Component
public class NegativeDirective implements CSDirectiveAware {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        DataFetcher originalFetcher = environment.getFieldDataFetcher();
        DataFetcher dataFetcher = DataFetcherFactories
                .wrapDataFetcher(originalFetcher, ((dataFetchingEnvironment, value) -> {
                    if (value instanceof Integer && (int) value >= 0) {
                        return -(int) value;
                    }
                    return value;
                }));

        environment.getCodeRegistry().dataFetcher(
                environment.getFieldsContainer(),
                environment.getFieldDefinition(),
                dataFetcher
        );
        return environment.getElement();
    }

    @Override
    public String getName() {
        return "negative";
    }
}
