package com.nikolaev.domain;

import org.springframework.http.HttpStatus;


import java.util.HashMap;


public class ApiError {

    private HttpStatus status;
    private String message;
    private HashMap<String, String> errors;

    public ApiError(HttpStatus status, String message, HashMap<String, String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String subject, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = new HashMap(){{put(subject, error);}};
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = new HashMap(){{put("error", error);}};
    }

    public ApiError() {}

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

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

}
