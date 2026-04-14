package com.main.java.features.userInfo.controller;

import com.main.java.common.ApiResponse;
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

/**
 * REST controller for user information management endpoints.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserInfoController {

	private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

	@GetMapping
	public ResponseEntity<ApiResponse<Page<UserInfoResponse>>> getUserInfos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<UserInfoResponse> response = userInfoService.getAll(page, size);
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserInfoResponse>> create(@Valid @RequestBody UserInfoRequest request) {
		UserInfoResponse response = userInfoService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserInfoResponse>> findById(@PathVariable UUID id) {
		UserInfoResponse response = userInfoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserInfoResponse>> update(@PathVariable UUID id, @Valid @RequestBody UserInfoRequest request) {
		UserInfoResponse response = userInfoService.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		userInfoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/batch")
	public ResponseEntity<String> deleteMany(@RequestBody List<UUID> ids) {
		userInfoService.deleteByMany(ids);
		return ResponseEntity.noContent().build();
	}

}
