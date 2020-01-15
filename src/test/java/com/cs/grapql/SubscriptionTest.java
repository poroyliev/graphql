package com.cs.grapql;

import com.cs.grapql.base.CSAbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionTest extends CSAbstractIntegrationTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void subscriptionTest() throws Exception {

        ObjectNode variables = mapper.createObjectNode();
        variables.put("int", 1);

        GraphQLResponse graphQLResponse = graphQLTestTemplate.perform("graphql/requests/subscription_vehicle.graphql", variables);
        Assertions.assertThat(graphQLResponse).isNotNull();

        String body = graphQLResponse.getRawResponse().getBody();
        Assertions.assertThat(body).isNotEmpty();
    }
}
