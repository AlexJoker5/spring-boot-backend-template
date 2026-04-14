package com.main.java.features.account.controller;

import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.features.account.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public AccountController(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> getAccounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<AccountResponse> response = accountService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request, HttpServletRequest httpServletRequest) {
        AccountResponse response = accountService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
        AccountResponse response =accountService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable UUID id, @Valid @RequestBody AccountRequest request, HttpServletRequest httpServletRequest) {
        AccountResponse response =accountService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account deleted successfully");
    }

    @DeleteMapping("/batch")
    public ResponseEntity<String> deteteMany(@RequestBody List<UUID> ids, HttpServletRequest httpServletRequest) {
        accountService.deletebyMany(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Accounts deleted successfully");
    }

}
