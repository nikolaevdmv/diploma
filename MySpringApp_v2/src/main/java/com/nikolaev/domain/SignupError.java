package com.nikolaev.domain;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

public class SignupError {

    private HttpStatus status;
    private String message;
    private HashMap<String, List<String>> errors;

    public SignupError(HttpStatus status, String message, HashMap<String, List<String>> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public SignupError(HttpStatus status, String message, String subject, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = new HashMap(){{put(subject, error);}};
    }

    public SignupError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = new HashMap(){{put("error", error);}};
    }

    public SignupError() {}

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, List<String>> errors) {
        this.errors = errors;
    }

}
