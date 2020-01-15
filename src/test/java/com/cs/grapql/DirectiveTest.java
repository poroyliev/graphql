package com.cs.grapql;

import com.cs.grapql.base.CSAbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

public class DirectiveTest extends CSAbstractIntegrationTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void directiveTest() throws Exception {
        int vehicleId = ThreadLocalRandom.current().nextInt();

        ObjectNode variables = mapper.createObjectNode();
        variables.set("tyre", mapper.createObjectNode().put("vehicleId", vehicleId));

        GraphQLResponse graphQLResponse = graphQLTestTemplate.perform("graphql/requests/create_tyre.graphql", variables);

        Assertions.assertThat(graphQLResponse).isNotNull();
        Assertions.assertThat(graphQLResponse.get("$.data.createTyre.id")).isNotNull();
        Assertions.assertThat(graphQLResponse.get("$.data.createTyre.vehicleId", Integer.class)).isNotNull();
        Assertions.assertThat(graphQLResponse.get("$.data.createTyre.vehicleId", Integer.class)).isEqualTo(-vehicleId);

    }
}