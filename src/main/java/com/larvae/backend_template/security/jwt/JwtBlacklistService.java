package com.larvae.backend_template.security.jwt;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JwtBlacklistService {

    private final RedisTemplate<String, Long> redisTemplate;

    public JwtBlacklistService(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void revokeToken(String token, long expiresAt){
        long totalMiliseconds = expiresAt - System.currentTimeMillis();
        if(totalMiliseconds > 0) {
            redisTemplate.opsForValue().set(token, expiresAt, Duration.ofMillis(totalMiliseconds));
        }
    }

    public boolean isTokenRevoked(String token){
        Long expiresAt = redisTemplate.opsForValue().get(token);
        return expiresAt != null && expiresAt > System.currentTimeMillis();
    }

    public void cleanUpExpiredToken(String token){
        redisTemplate.delete(token);
    }

}
