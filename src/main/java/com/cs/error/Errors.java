package com.cs.error;

import graphql.GraphQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class Errors {

    @ExceptionHandler(GraphQLException.class)
    public ResponseEntity<String> handleError() {
        return ResponseEntity.status(500).body("test");
    }
}
