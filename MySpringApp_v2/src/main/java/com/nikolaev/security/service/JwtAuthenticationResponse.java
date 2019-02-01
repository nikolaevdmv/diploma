package com.nikolaev.security.service;

import com.nikolaev.user.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {


    private final String token;
    private final UserDto userDto;

    public JwtAuthenticationResponse(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }

    public JwtAuthenticationResponse(String token) {
        this.token = token;
        userDto = null;
    }

    public String getToken() {
        return this.token;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}