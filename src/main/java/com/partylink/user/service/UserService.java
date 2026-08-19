package com.partylink.user.service;

import com.partylink.exception.BusinessException;
import com.partylink.role.entity.Role;
import com.partylink.role.repository.RoleRepository;
import com.partylink.role.service.AgeValidator;
import com.partylink.user.Entity.User;
import com.partylink.user.Entity.UserStatus;
import com.partylink.user.dto.RegisterRequest;
import com.partylink.user.dto.RegisterResponse;
import com.partylink.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AgeValidator ageValidator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        if (!ageValidator.isAdult(request.dateOfBirth())) {
            throw new BusinessException(
                    "User must be at least 18 years old"
            );
        }

        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new BusinessException(
                    "Email is already registered"
            );
        }

        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() ->
                        new IllegalStateException("CUSTOMER role not found")
                );

        User user = User.builder()
                .name(request.name())
                .email(request.email().trim().toLowerCase())
                .password(passwordEncoder.encode(request.password()))
                .phone(request.phone())
                .dateOfBirth(request.dateOfBirth())
                .status(UserStatus.ACTIVE)
                .build();

        user.getRoles().add(customerRole);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getDateOfBirth()
        );
    }
}
