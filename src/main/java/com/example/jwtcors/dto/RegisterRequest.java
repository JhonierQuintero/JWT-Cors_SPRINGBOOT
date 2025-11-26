package com.example.jwtcors.dto;

import com.example.jwtcors.entity.Role;

public record RegisterRequest(String username,
                              String password,
                              Role role) {
}
