package com.partylink.user.controller;

import com.partylink.user.dto.RegisterRequest;
import com.partylink.user.dto.RegisterResponse;
import com.partylink.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        return "Authenticated user ID: " + authentication.getName();
    }
}
