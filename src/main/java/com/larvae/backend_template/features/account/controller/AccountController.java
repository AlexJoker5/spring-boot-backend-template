package com.larvae.backend_template.features.account.controller;

import com.larvae.backend_template.common.ApiResponse;
import com.larvae.backend_template.features.account.dto.request.AccountRequest;
import com.larvae.backend_template.features.account.dto.response.AccountResponse;
import com.larvae.backend_template.features.account.service.AccountService;
import com.larvae.backend_template.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for account management endpoints.
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final AccountService accountService;

    public AccountController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, String>>  login(@RequestBody AccountRequest accountRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountRequest.username(), accountRequest.password())
        );
        String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities().iterator().next().getAuthority());
        return ApiResponse.success(Map.of("token", token));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AccountResponse>>> getAccounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<AccountResponse> response = accountService.getAll(page, size);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> create(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> findById(@PathVariable UUID id) {
        AccountResponse response =accountService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> update(@PathVariable UUID id, @Valid @RequestBody AccountRequest request) {
        AccountResponse response =accountService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/actions/batch-delete")
    public ResponseEntity<Void> deleteMany(@RequestBody List<UUID> ids) {
        accountService.deleteByMany(ids);
        return ResponseEntity.noContent().build();
    }

}
