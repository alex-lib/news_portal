package com.example.springbootnewsportal.validation.news;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NewsFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewsFilterValid {

    String message() default "Fields of pagination must be pointed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}