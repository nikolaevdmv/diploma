package com.nikolaev.security.service;

import com.nikolaev.security.JwtUserFactory;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("in loadUserByUsername()");
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            logger.info("loadUserByUsername(): username: " + user.getUsername());
            logger.info("loadUserByUsername(): password: " + user.getPassword());
            logger.info("loadUserByUsername(): jwtUser: " + JwtUserFactory.create(user));
            return JwtUserFactory.create(user);
        }
    }

}
