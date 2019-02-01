package com.nikolaev.security.signup;

import com.nikolaev.security.service.JwtAuthenticationResponse;
import org.springframework.mobile.device.Device;

public interface SignupService {
    JwtAuthenticationResponse signup(SignupRequest signupRequest, Device device) throws SignupException;
}
