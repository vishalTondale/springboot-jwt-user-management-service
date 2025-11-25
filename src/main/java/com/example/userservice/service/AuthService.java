package com.example.userservice.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.JwtResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.util.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // Registration
    public ApiResponse<?> register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            return new ApiResponse<>(false, "Email already in use", null);
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (req.getRoles() == null || req.getRoles().isEmpty()) {
            // default role
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow();
            roles.add(userRole);
        } else {
            for (String r : req.getRoles()) {
                String roleName = r.startsWith("ROLE_") ? r : "ROLE_" + r.toUpperCase();
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow();
                roles.add(role);
            }
        }

        user.setRoles(roles);
        userRepository.save(user);

        return new ApiResponse<>(true, "User registered successfully", null);
    }

    // Login
    public JwtResponse login(LoginRequest req) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        var principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(principal.getUsername());

        return new JwtResponse(token);
    }
}
