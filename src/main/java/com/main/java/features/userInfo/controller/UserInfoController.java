package com.main.java.features.userInfo.controller;

import com.main.java.features.account.service.AccountService;
import com.main.java.features.userInfo.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.service.impl.UserInfoServiceImpl;

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

    @GetMapping("/register")
	public String getRegisterForm() {
		return "Register Form";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUserInfo(@Valid @RequestBody UserInfoRequest request) {
	    System.out.println("Register endpoint hit!");
		try {
			userInfoService.create(request);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.ok("Employee created successfully");
	}

}
