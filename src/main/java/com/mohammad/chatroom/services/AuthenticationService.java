package com.mohammad.chatroom.services;

import com.mohammad.chatroom.dto.auth.AuthResponse;
import com.mohammad.chatroom.dto.auth.LoginDto;
import com.mohammad.chatroom.dto.auth.RegisterDto;

public interface AuthenticationService {
    public AuthResponse login(LoginDto loginDto);

    public AuthResponse register(RegisterDto registerDto);
}
