package com.main.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration for request authorization and login handling.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	/**
	 * Configures the security filter chain for the application.
	 *
	 * @param http the HttpSecurity builder
	 * @return configured SecurityFilterChain
	 * @throws Exception if configuration fails
	 */
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/**").permitAll()
						.requestMatchers("/error").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/v3/api-docs/**").permitAll()
						.anyRequest().authenticated()
				)
			
				.build();
	}

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
