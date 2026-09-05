package com.partylink.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.partylink.user.entity.User;
import com.partylink.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTVerifier jwtVerifier;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(@Value("${security.jwt.secret}") String secret, UserRepository userRepository){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        this.jwtVerifier = JWT.require(algorithm).build();
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);

        try{
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String userIdStr = decodedJWT.getSubject();

            UUID userId = UUID.fromString(userIdStr);
            User user = userRepository.findByIdWithRoles(userId).orElse(null);

            if(user != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        }catch (JWTVerificationException | IllegalArgumentException exception){

            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);

    }
}
