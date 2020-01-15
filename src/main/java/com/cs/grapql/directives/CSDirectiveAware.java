package com.cs.grapql.directives;

import graphql.schema.idl.SchemaDirectiveWiring;

/**
 * This interface provides entry point for creating GraphQL directives.
 * Implementations must be {@link org.springframework.stereotype.Component} and will be
 * picked <b>automatically</b>.<br/>
 * Example:
 * <pre>
 * {@code
 *  @Component
 *  public class NegativeDirective implements CSIDirectiveAware {
 *       @Override
 *       public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
 *           DataFetcher originalFetcher = environment.getFieldDataFetcher();
 *           DataFetcher dataFetcher = DataFetcherFactories
 *                   .wrapDataFetcher(originalFetcher, ((dataFetchingEnvironment, value) -> {
 *                       if (value instanceof Integer && (int) value >= 0) {
 *                           return -(int)value;
 *                       }
 *                       return value;
 *                   }));
 *
 *           environment.getCodeRegistry().dataFetcher(
 *                   environment.getFieldsContainer(),
 *                   environment.getFieldDefinition(),
 *                   dataFetcher
 *           );
 *           return environment.getElement();
 *       }
 *
 *       @Override
 *       public String getName() {
 *           return "negative";
 *       }
 *   }
 *
 * }</pre>
 */
public interface CSDirectiveAware extends SchemaDirectiveWiring {
    String getName();
}
