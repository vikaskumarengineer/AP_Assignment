package com.onboarding;

public class InvalidEmailException extends Exception {

    private final String invalidEmail;

    public InvalidEmailException(String email, String reason) {
        super(String.format("Invalid email '%s': %s", email, reason));
        this.invalidEmail = email;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }
}