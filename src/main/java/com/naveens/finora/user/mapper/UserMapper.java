package com.naveens.finora.user.mapper;

import com.naveens.finora.auth.dto.response.RegisterRequestDto;
import com.naveens.finora.auth.dto.response.UserResponseDto;
import com.naveens.finora.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(RegisterRequestDto dto){
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCurrency(dto.getCurrency());

        return user;
    }

    public UserResponseDto toResponse(User user){

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCurrency(user.getCurrency());

        return response;

    }
}
