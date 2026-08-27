package com.partylink.auth.service;

import com.partylink.auth.dto.LoginRequest;
import com.partylink.auth.dto.LoginResponse;
import com.partylink.exception.BusinessException;
import com.partylink.user.entity.User;
import com.partylink.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow( () ->
                        new BusinessException("Invalid email or password")
                );

        if (!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new BusinessException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);

    }
}
