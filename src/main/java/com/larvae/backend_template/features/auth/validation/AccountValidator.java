package com.larvae.backend_template.features.auth.validation;

import com.larvae.backend_template.entity.Account;
import com.larvae.backend_template.features.auth.dto.request.AccountRequest;
import com.larvae.backend_template.features.auth.repository.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<ValidateAccountType, AccountRequest> {

    private final AccountRepository accountRepo;

    public AccountValidator(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public boolean isValid(AccountRequest request, ConstraintValidatorContext context) {

        if(request.username() != null) {
            Account account = accountRepo.findByUsernameAndIsActiveTrue(request.username()).orElseGet(Account::new);
            if(request.username().equals(account.getUsername())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("User with username: " + request.username() + " already exists")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
