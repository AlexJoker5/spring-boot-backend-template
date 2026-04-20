package com.larvae.backend_template.security;

import com.larvae.backend_template.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration for request authorization and login handling.
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	private static final String [] PUBLIC_ENDPOINTS = {
			"/api/v1/home",
			"/api/v1/login",
			"/error",
			"/swagger-ui/**",
			"/swagger-ui/index.html",
			"/v3/api-docs/**"
	};

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
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.defaultSuccessUrl("/api/v1/users")
						.permitAll()
				)
				.logout(logout -> logout
						.logoutSuccessUrl("/login")
						.permitAll()
				)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		try {
			return config.getAuthenticationManager();
		} catch (Exception e) {
			throw new RuntimeException("Failed to create AuthenticationManager", e);
		}
	}

}
