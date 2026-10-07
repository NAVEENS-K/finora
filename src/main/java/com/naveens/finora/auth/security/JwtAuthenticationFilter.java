package com.naveens.finora.auth.security;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException{
        String authorizationHeader =
                request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token =
                authorizationHeader.substring(7);

        try{
            Long userId = jwtService.extractUserId(token);

            System.out.println("JWT Valid");
            System.out.println("User ID FROM JWT: "+ userId);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            System.out.println("AUTHENTICATION: "+SecurityContextHolder.getContext().getAuthentication());

        } catch(Exception e){

            System.out.println("JWT Validation failed");
            e.printStackTrace();
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
