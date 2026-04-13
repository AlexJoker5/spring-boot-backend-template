package com.main.java.features.account.controller;

import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "Register Form";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAccountInfo(@Valid @RequestBody AccountRequest request) {
        try{
            accountService.create(request);
        } catch (Exception e) {
            throw e;
        }
        return ResponseEntity.ok("Account created successfully");
    }

}
