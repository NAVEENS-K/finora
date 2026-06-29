package com.naveens.finora.auth.service;

import com.naveens.finora.auth.dto.response.UserResponseDto;
import com.naveens.finora.auth.dto.request.RegisterRequestDto;
import com.naveens.finora.exception.EmailAlreadyExistsException;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.mapper.UserMapper;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto register(RegisterRequestDto request){
        boolean emailExists = userRepository.existsByEmail(request.getEmail());
        if(emailExists){
            throw new EmailAlreadyExistsException("Email Already Exists.");
        }
        else{
            User user = userMapper.toEntity(request);

            User savedUser = userRepository.save(user);

            return userMapper.toResponse(savedUser);
        }
    }
}
