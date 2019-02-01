package com.nikolaev.security.signin;

import com.nikolaev.security.JwtTokenUtil;
import com.nikolaev.security.service.JwtAuthenticationRequest;
import com.nikolaev.security.service.JwtAuthenticationResponse;
import com.nikolaev.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SigninServiceImpl implements SigninService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public JwtAuthenticationResponse signin(JwtAuthenticationRequest authenticationRequest, Device device) {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

//        return new JwtAuthenticationResponse(token, userDetails.getUsername());

        return new JwtAuthenticationResponse(token, UserMapper.toDto(userDetails));
//        return new JwtAuthenticationResponse(token, userDetails);
    }
}
