package com.example.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // ---------------- ADMIN: Get All Users ----------------
    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers(Authentication auth) {

        boolean isAdmin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(403).body(
                    new ApiResponse(false, "Access denied - Admin only", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse(true, "Users fetched successfully", userRepository.findAll())
        );
    }

    // ---------------- ADMIN or SELF: Get One User ----------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable Long id, Authentication auth) {

        String currentUserId = auth.getName();

        boolean isAdmin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !currentUserId.equals(id.toString())) {
            return ResponseEntity.status(403).body(
                    new ApiResponse(false, "Access denied", null)
            );
        }

        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(
                        new ApiResponse(true, "User fetched successfully", user)))
                .orElse(ResponseEntity.status(404)
                        .body(new ApiResponse(false, "User not found", null)));
    }

    // ---------------- ADMIN: Delete User ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id, Authentication auth) {

        boolean isAdmin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(403)
                    .body(new ApiResponse(false, "Access denied - Admin only", null));
        }

        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse(false, "User not found", null));
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok(
                new ApiResponse(true, "User deleted successfully", null)
        );
    }
}
