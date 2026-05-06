package com.larvae.backend_template.features.auth.validation;

import com.larvae.backend_template.entity.Account;
import com.larvae.backend_template.features.auth.dto.request.AccountRequest;
import com.larvae.backend_template.features.auth.repository.AccountRepository;
import com.larvae.backend_template.security.AccountDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.UUID;

public class AccountValidator implements ConstraintValidator<ValidateAccountType, AccountRequest> {

    private final AccountRepository accountRepo;

    public AccountValidator(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public boolean isValid(AccountRequest request, ConstraintValidatorContext context) {

        if(request.username() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !auth.isAuthenticated()) {
                return true;
            }

            UUID id = ((AccountDetails) auth.getPrincipal()).getId();

            Account existingAccount = accountRepo.findByUsernameAndIsActiveTrue(request.username()).orElse(null);

            boolean isUpdate = existingAccount != null && existingAccount.getId().equals(id);

            if(!isUpdate && existingAccount != null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("User with username: " + request.username() + " already exists")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
