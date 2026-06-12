package com.onboarding;

import java.util.regex.Pattern;

public class RegistrationService {

    public static final int MINIMUM_AGE = 18;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+\\-]+@[\\w\\-]+(\\.[\\w\\-]+)+$");

    private final boolean initialised;

    public RegistrationService() {
        this.initialised = true;
    }

    RegistrationService(boolean initialised) {
        this.initialised = initialised;
    }

    public boolean registerUser(String email, int age) throws InvalidEmailException {

        assert initialised : "RegistrationService has not been properly initialised!";

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException(email, "Email must not be null or empty");
        }

        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new InvalidEmailException(email,
                    "Email does not conform to the required format (e.g. user@example.com)");
        }

        if (age < MINIMUM_AGE) {
            throw new UnderageException(age, MINIMUM_AGE);
        }

        return true;
    }
}