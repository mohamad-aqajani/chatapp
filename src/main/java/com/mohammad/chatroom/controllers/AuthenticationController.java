package com.mohammad.chatroom.controllers;

import com.mohammad.chatroom.dto.auth.AuthResponse;
import com.mohammad.chatroom.dto.auth.LoginDto;
import com.mohammad.chatroom.dto.auth.RegisterDto;
import com.mohammad.chatroom.services.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody(required = true) LoginDto loginDto) {
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody(required = true) RegisterDto registerDto) {
        return new ResponseEntity<>(authenticationService.register(registerDto), HttpStatus.CREATED);
    }
}
