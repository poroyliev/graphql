package com.cs.grapql;

import com.cs.grapql.base.CSAbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class ValidationTest extends CSAbstractIntegrationTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void firstPositionTargetArgTest() throws Exception {

        int vehicleId = ThreadLocalRandom.current().nextInt();

        ObjectNode variables = mapper.createObjectNode();
        ObjectNode tyreInput = mapper.createObjectNode().put("vehicleId", vehicleId);
        tyreInput.put("brand", RandomStringUtils.randomAlphabetic(6));
        variables.set("tyre", tyreInput);


        testPosition(variables, "graphql/requests/createTyreFirstArg.graphql");
        testPosition(variables, "graphql/requests/createTyreOnlyArg.graphql");
        testPosition(variables, "graphql/requests/createTyreLastArg.graphql");

    }

    private void testPosition(ObjectNode variables, String path) throws IOException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.perform(path, variables);

        Assertions.assertThat(graphQLResponse).isNotNull();
        Assertions.assertThat(graphQLResponse.get("$.errors[0].message")).contains("[brand] size must be between 0 and 5");
    }
}

