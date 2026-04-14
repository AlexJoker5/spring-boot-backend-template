package com.main.java.features.account.validation;

import com.main.java.exceptions.AlreadyExistsException;
import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.features.account.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<ValidateAccountType, AccountRequest> {

    private final AccountService accountService;

    public AccountValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean isValid(AccountRequest request, ConstraintValidatorContext context) {

        if(request.username() != null) {
            AccountResponse response = accountService.findByUsername(request.username());
            if(request.username().equals(response.username())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("User with username: " + request.username() + " already exists")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
