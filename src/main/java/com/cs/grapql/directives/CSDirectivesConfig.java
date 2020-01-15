package com.cs.grapql.directives;

import graphql.kickstart.tools.boot.SchemaDirective;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CSDirectivesConfig {

    private final List<CSDirectiveAware> directives;
    private final ConfigurableApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        Optional.ofNullable(directives)
                .ifPresent(csiDirectives -> {
                    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
                    csiDirectives.stream()
                            .map(csiDirective -> new SchemaDirective(csiDirective.getName(), csiDirective))
                            .forEach(schemaDirective -> beanFactory.registerSingleton(schemaDirective.getClass().getCanonicalName(), schemaDirective));
                });
    }

}
