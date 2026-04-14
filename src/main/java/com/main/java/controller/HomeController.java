package com.main.java.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple health endpoint used to verify that the backend is running.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {
	
	/**
	 * Returns a simple greeting message for health checks.
	 *
	 * @return hello world string
	 */
	@GetMapping(value = "/api/home")
	public String home() {
		return "Hello World";
	}

}
