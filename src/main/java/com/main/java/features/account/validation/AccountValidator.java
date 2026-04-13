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

        if(request.getUsername() != null) {
            AccountResponse response = accountService.findByUsername(request.getUsername());
            if(request.getUsername().equals(response.getUsername())) {
                throw new AlreadyExistsException("Username", "DUPLICATE_DATA", "User with username: " + request.getUsername() + " is already existed");
            }
        }

        return true;
    }
}
