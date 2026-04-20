package com.larvae.backend_template.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple health endpoint used to verify that the backend is running.
 */
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
	
	/**
	 * Returns a simple greeting message for health checks.
	 *
	 * @return hello world string
	 */
	@GetMapping
	public String home() {
		return "Hello World";
	}

}
