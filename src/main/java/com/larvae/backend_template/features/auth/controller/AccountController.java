package com.larvae.backend_template.features.auth.controller;

import com.larvae.backend_template.common.ApiResponse;
import com.larvae.backend_template.features.auth.dto.request.AccountRequest;
import com.larvae.backend_template.features.auth.dto.request.LoginRequest;
import com.larvae.backend_template.features.auth.dto.response.AccountResponse;
import com.larvae.backend_template.features.auth.service.AccountService;
import com.larvae.backend_template.security.jwt.JwtBlacklistService;
import com.larvae.backend_template.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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
 * REST controller for auth management endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final AccountService accountService;
    private final JwtBlacklistService jwtBlacklistService;

    public AccountController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, AccountService accountService, JwtBlacklistService jwtBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>>  login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );
        String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities().iterator().next().getAuthority());
        return ResponseEntity.ok(ApiResponse.success(Map.of("token", token)));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            try{
                jwtBlacklistService.revokeToken(token, jwtTokenUtil.extractExpiration(token).getTime());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ApiResponse.error("Logout failed: token blacklist unavailable"));
            }
        }
        return ResponseEntity.ok(ApiResponse.success("Logged out successfully", null));
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
        AccountRequest requestWithId = request.toBuilder().id(id).build();
        AccountResponse response =accountService.update(id, requestWithId);
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
