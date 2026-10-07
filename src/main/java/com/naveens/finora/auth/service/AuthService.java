package com.naveens.finora.auth.service;

import com.naveens.finora.auth.dto.request.LoginRequestDto;
import com.naveens.finora.auth.dto.response.LoginResponseDto;
import com.naveens.finora.auth.dto.response.UserResponseDto;
import com.naveens.finora.auth.dto.request.RegisterRequestDto;
import com.naveens.finora.auth.security.JwtService;
import com.naveens.finora.exception.EmailAlreadyExistsException;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.mapper.UserMapper;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDto register(RegisterRequestDto request){
        boolean emailExists = userRepository.existsByEmail(request.getEmail());
        if(emailExists) {
            throw new EmailAlreadyExistsException("Email Already Exists.");
        }


            User user = userMapper.toEntity(request);

            String encodedPassword = passwordEncoder.encode(request.getPassword());

            user.setPassword(encodedPassword);

            User savedUser = userRepository.save(user);

            return userMapper.toResponse(savedUser);
    }

    public LoginResponseDto login(LoginRequestDto request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid email or password."));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return LoginResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
