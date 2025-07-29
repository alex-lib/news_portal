package com.example.springbootnewsportal.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserFilterValid {

    String message() default "Fields of pagination must be pointed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}