package com.partylink.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.partylink.role.entity.Role;
import com.partylink.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtService {

    private final Algorithm algorithm;
    private final long expiration;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration}") long expiration
    ){
        this.algorithm = Algorithm.HMAC256(secret);
        this.expiration = expiration;
    }

    public String generateToken(User user){
        Instant now = Instant.now();
        Instant expiresAt = now.plusMillis(expiration);

        List<String> rolesList = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("roles", rolesList)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }
}
