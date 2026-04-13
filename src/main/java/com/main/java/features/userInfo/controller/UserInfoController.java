package com.main.java.features.userInfo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.service.impl.UserInfoServiceImpl;
import com.main.java.repository.AccountRepo;


@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class UserInfoController {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	@GetMapping("/register")
	public String getRegisterForm() {
		return "Register Form";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerEmployeeInfo(@Valid @RequestBody UserInfoRequest request) {
	    System.out.println("Register endpoint hit!");
		try {
			userInfoServiceImpl.create(request);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.ok("Employee created successfully");
	}

}
