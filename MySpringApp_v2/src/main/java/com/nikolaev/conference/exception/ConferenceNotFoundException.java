package com.nikolaev.conference.exception;

public class ConferenceNotFoundException extends Exception {
    public ConferenceNotFoundException() {
    }

    public ConferenceNotFoundException(String message) {
        super(message);
    }
}
