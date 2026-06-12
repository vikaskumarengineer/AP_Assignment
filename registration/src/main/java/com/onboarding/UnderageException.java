package com.onboarding;

public class UnderageException extends RuntimeException {

    private final int applicantAge;
    private final int minimumAge;

    public UnderageException(int applicantAge, int minimumAge) {
        super(String.format(
                "Applicant age %d does not meet the minimum age requirement of %d.",
                applicantAge, minimumAge));
        this.applicantAge = applicantAge;
        this.minimumAge   = minimumAge;
    }

    public int getApplicantAge() { return applicantAge; }
    public int getMinimumAge()   { return minimumAge;   }
}