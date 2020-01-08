package com.cs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void theTest() throws IOException {
        ObjectNode variables = mapper.createObjectNode();
        variables.put("type","car");
        variables.put("modelCode","XYZ0192");
        variables.put("brandName","XYZ");
        variables.put("launchDate","2016-08-16");

        GraphQLResponse graphQLResponse = graphQLTestTemplate.perform("graphql/create_vehicle.graphql", variables);

        Assertions.assertThat(graphQLResponse).isNotNull();
        Assertions.assertThat(graphQLResponse.get("$.data.createVehicle.id")).isNotNull();
        Assertions.assertThat(graphQLResponse.isOk()).isTrue();
    }
}