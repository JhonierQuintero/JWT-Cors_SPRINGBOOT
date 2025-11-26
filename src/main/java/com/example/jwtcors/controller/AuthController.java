package com.example.jwtcors.controller;

import com.example.jwtcors.dto.LoginRequest;
import com.example.jwtcors.dto.RegisterRequest;
import com.example.jwtcors.entity.User;
import com.example.jwtcors.service.JwtUtil;
import com.example.jwtcors.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        return userService.register(
                request.username(),
                request.password(),
                request.role()
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){

        User u = userService.findByUsername(request.username());

        if (u == null || !userService.checkPassword(request.password(), u.getPassword())) {
            throw new RuntimeException("Credenciales Invalidas");
        }

        return jwtUtil.generateToken(u.getUsername(), u.getRole());
    }


}
