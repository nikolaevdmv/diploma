package com.nikolaev.web;

import com.nikolaev.security.JwtTokenUtil;
import com.nikolaev.security.JwtUser;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import com.nikolaev.user.dto.UserDto;
import com.nikolaev.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api")
public class UserRestController {

//    @Value("${jwt.header}")
    private String tokenHeader="Authorization";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public JwtUser getAuthenticatedUser(HttpServlet   Request request) {
//        String token = request.getHeader(tokenHeader);
//        if (token!=null && token.startsWith("Bearer "))
//            token = token.substring(7);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//        return user;
//    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserDto getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token!=null && token.startsWith("Bearer "))
            token = token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username);
        return UserMapper.toDto(user);
    }
}
