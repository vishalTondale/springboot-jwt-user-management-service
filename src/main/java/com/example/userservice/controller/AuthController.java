package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.JwtResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.AuthService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserRepository userRepository;

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterRequest request) {
        ApiResponse<?> response = authService.register(request);
        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request) {
        JwtResponse jwt = authService.login(request);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Login successful", jwt)
        );
    }

    // ---------------- ME ----------------
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> getCurrentUser(Authentication auth) {

        if (auth == null) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "Unauthorized - Missing or invalid token", null));
        }

        User user = userRepository.findByEmail(auth.getName())
                .orElse(null);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User profile fetched successfully", user)
        );
    }
}
