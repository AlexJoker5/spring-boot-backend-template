package com.larvae.backend_template.security.jwt;

import com.larvae.backend_template.security.AccountDetails;
import com.larvae.backend_template.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtBlacklistService blacklistService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, JwtBlacklistService blacklistService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.blacklistService = blacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header =  request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            try{
                String username = jwtUtil.extractUsername(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if(!blacklistService.isTokenRevoked(token) &&
                            jwtUtil.isTokenValid(token, username)) {

                        String role = jwtUtil.extractRole(token);
                        AccountDetails accountDetails = (AccountDetails) customUserDetailsService.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                accountDetails, null, List.of(new SimpleGrantedAuthority(role))
                        );
                        SecurityContextHolder.getContext().setAuthentication(auth);

                    }
                }
            } catch (Exception e) {
                logger.error("JWT authentication failed: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }
}
