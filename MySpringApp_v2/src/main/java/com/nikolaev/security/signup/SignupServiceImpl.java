package com.nikolaev.security.signup;

import com.nikolaev.security.service.JwtAuthenticationResponse;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import com.nikolaev.security.JwtTokenUtil;
import com.nikolaev.user.dto.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;


@Service
public class SignupServiceImpl implements SignupService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    public JwtAuthenticationResponse signup(SignupRequest signupRequest, Device device) throws SignupException {
        User user = new User();
        user.setUsername(signupRequest.getEmail());
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setPassword(signupRequest.getPassword());
        user.setEmail(signupRequest.getEmail());
        if (validate(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmationToken(jwtTokenUtil.generateToken(user));
            user.setEnabled(true);
            user.setLastPasswordResetDate(new Date());
            user.setConfirmed(false);
            userRepository.save(user);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            ));

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails, device);

//            return new JwtAuthenticationResponse(token, userDetails.getUsername());
            return new JwtAuthenticationResponse(token, UserMapper.toDto(userDetails));
        }
        return null;
    }

    private boolean validate(User user) throws SignupException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new SignupException("Something went wrong",
                    new HashMap<String, List<String>>() {
                        {
//                            put("email", Arrays.asList("Email already exists"));
                            put("email", Arrays.asList("Пользователь с таким Email уже существует"));
                        }
                    });
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.isEmpty())
            return true;
        HashMap<String, List<String>> errors = new HashMap<>();

        for (ConstraintViolation<User> violation : violations) {
            if (errors.get(violation.getPropertyPath().toString()) == null)
                errors.put(violation.getPropertyPath().toString(), new ArrayList<>());
            errors.get(violation.getPropertyPath().toString()).add(violation.getMessage());
        }


        throw new SignupException("Something went wrong", errors);
    }
}
