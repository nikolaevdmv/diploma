package com.nikolaev.web;


import com.nikolaev.domain.ApiError;
import com.nikolaev.domain.SignupError;
import com.nikolaev.security.signup.SignupException;
import com.nikolaev.user.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuth(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN,
                e.getMessage(),
                e.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                e.getMessage(),
                e.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<Object> handleSignup(SignupException e) {
        SignupError signupError = new SignupError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getErrors());
        return new ResponseEntity<Object>(signupError, new HttpHeaders(), signupError.getStatus());
    }


}
