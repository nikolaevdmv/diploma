package com.nikolaev.security.signup;

import java.util.HashMap;
import java.util.List;

public class SignupException extends Exception {

    private HashMap<String, List<String>> errors;
    public SignupException(String message) {
        super(message);
    }

    public SignupException(String message, HashMap<String, List<String>> errors) {
        super(message);
        this.errors = errors;
    }

    public HashMap<String, List<String>> getErrors() {
        return errors;
    }
}
