package com.naveens.finora.auth.service;

import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public User getCurrectUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException("User not authenticated.");
        }
        Long userId = (Long) authentication.getPrincipal();

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }
}
