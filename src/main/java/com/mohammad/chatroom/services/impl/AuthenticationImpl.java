package com.mohammad.chatroom.services.impl;

import com.mohammad.chatroom.dto.auth.AuthResponse;
import com.mohammad.chatroom.dto.auth.LoginDto;
import com.mohammad.chatroom.dto.auth.RegisterDto;
import com.mohammad.chatroom.dto.auth.UserDto;
import com.mohammad.chatroom.entities.Role;
import com.mohammad.chatroom.entities.User;
import com.mohammad.chatroom.exceptions.BadRequestException;
import com.mohammad.chatroom.exceptions.NotFoundException;
import com.mohammad.chatroom.repositories.RoleRepository;
import com.mohammad.chatroom.repositories.UserRepository;
import com.mohammad.chatroom.security.JWTtokenProvider;
import com.mohammad.chatroom.services.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private JWTtokenProvider jwtTokenProvider;


    @Override
    public AuthResponse login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), loginDto.getPassword())) {
            throw new BadRequestException("Wrong Password");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(loginDto.getUsername());
        UserDto responseUser = new UserDto() {{
            setId(user.getId());
            setUsername(user.getUsername());
            setEmail(user.getEmail());
        }};
        return new AuthResponse() {{
            setAccessToken(token);
            setUser(responseUser);
        }};
    }

    @Override
    public AuthResponse register(RegisterDto registerDto) {
        User user = new User();
        try {
            BeanUtils.copyProperties(user, registerDto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        if (user.getPassword() != null)
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow(
                () -> new BadRequestException("Role not found")
        );
        Set<Role> roles = new HashSet<>() {{
            add(role);
        }};

        user.setRoles(roles);
        User newUser = userRepository.save(user);
        UserDto responseUser = new UserDto() {{
            setId(newUser.getId());
            setUsername(newUser.getUsername());
            setEmail(newUser.getEmail());
        }};
        return new AuthResponse() {{
            setAccessToken(jwtTokenProvider.generateToken(newUser.getUsername()));
            setUser(responseUser);
        }};
    }
}
