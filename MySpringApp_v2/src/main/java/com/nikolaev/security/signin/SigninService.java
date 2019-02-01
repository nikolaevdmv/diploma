package com.nikolaev.security.signin;

import com.nikolaev.security.service.JwtAuthenticationRequest;
import com.nikolaev.security.service.JwtAuthenticationResponse;
import org.springframework.mobile.device.Device;

public interface SigninService {
    JwtAuthenticationResponse signin(JwtAuthenticationRequest authenticationRequest, Device device);
}
