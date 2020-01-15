package com.cs.grapql.validations;

import com.cs.grapql.error.CSGraphQLError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class CSValidationAspect {

    private final Validator validator;

    @Before(value = "within(com.cs.grapql.*.*) && execution(* *(.., @javax.validation.Valid (*), ..))")
    public void validate1(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        if(ArrayUtils.isEmpty(parameterAnnotations)) return;

        List<Errors> errorList = new ArrayList<>();
        populateErrors(joinPoint, parameterAnnotations, errorList);

        if (!errorList.isEmpty()) {
            StringBuilder errorMessageBuilder = new StringBuilder();
            errorList.forEach(err -> generateErrorMessage(err, errorMessageBuilder));

            String errorString = errorMessageBuilder.toString();
            log.error("{}", errorString);
            throw new CSGraphQLError(errorString);
        }
    }

    private void populateErrors(JoinPoint joinPoint, Annotation[][] parameterAnnotations, List<Errors> errorList) {
        for (int targetIndex = 0; targetIndex < parameterAnnotations.length; ++targetIndex) {
            Annotation[] annotations = parameterAnnotations[targetIndex];
            if (Arrays.stream(annotations).anyMatch(annotation -> annotation instanceof Valid)) {
                Object target = joinPoint.getArgs()[targetIndex];
                BindingResult errors = new BeanPropertyBindingResult(target, target.getClass().getSimpleName());
                validator.validate(target, errors);
                if (errors.hasErrors()) {
                    errorList.add(errors);
                }
            }
        }
    }

    private void generateErrorMessage(Errors errors, StringBuilder errorMessageBuilder) {
        errors.getFieldErrors()
                .forEach(
                        err -> errorMessageBuilder.append("[")
                                .append(err.getField())
                                .append("] ")
                                .append(err.getDefaultMessage())
                                .append("\n"));
    }
}
