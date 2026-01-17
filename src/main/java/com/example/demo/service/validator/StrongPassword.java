package com.example.demo.service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

    String message() default
            "Password must be at least 8 characters and include uppercase, lowercase, number and special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
