package com.main.java.features.userInfo.controller;

import com.main.java.features.account.service.AccountService;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import com.main.java.features.userInfo.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.java.features.userInfo.dto.request.UserInfoRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserInfoController {

	private final AccountService accountService;
	private final UserInfoService userInfoService;

    public UserInfoController(AccountService accountService, UserInfoService userInfoService) {
        this.accountService = accountService;
        this.userInfoService = userInfoService;
    }

	@GetMapping
	public ResponseEntity<Page<UserInfoResponse>> getUserInfos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<UserInfoResponse> response = userInfoService.getAll(page, size);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@PostMapping
	public ResponseEntity<UserInfoResponse> create(@Valid @RequestBody UserInfoRequest request, HttpServletRequest httpServletRequest) {
		UserInfoResponse response = userInfoService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserInfoResponse> findById(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
		UserInfoResponse response = userInfoService.findById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserInfoResponse> update(@PathVariable UUID id, @Valid @RequestBody UserInfoRequest request, HttpServletRequest httpServletRequest) {
		UserInfoResponse response = userInfoService.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
		userInfoService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Info deleted successfully");
	}

	@DeleteMapping("/batch")
	public ResponseEntity<String> deleteMany(@RequestBody List<UUID> ids, HttpServletRequest httpServletRequest) {
		userInfoService.deletebyMany(ids);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Infos deleted successfully");
	}

}
