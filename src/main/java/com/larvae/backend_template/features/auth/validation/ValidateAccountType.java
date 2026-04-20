package com.larvae.backend_template.features.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountValidator.class)
public @interface ValidateAccountType {

    String message() default "Invalid auth configuration";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
