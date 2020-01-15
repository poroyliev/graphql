package com.cs.grapql.base;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {CSAbstractIntegrationTest.Initializer.class})
public class CSAbstractIntegrationTest {

    private static MySQLContainer<?> mySQLContainer;

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8")
                    .withDatabaseName("integration-tests-db")
                    .withUsername("sa")
                    .withPassword("sa");

            mySQLContainer.start();
            CSAbstractIntegrationTest.mySQLContainer = mySQLContainer;

            TestPropertyValues.of(
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLContainer.getUsername(),
                    "spring.datasource.password=" + mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    public static MySQLContainer<?> getDockerContainer() {
        return mySQLContainer;
    }
}
