package com.onboarding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RegistrationService – full validation suite")
class RegistrationServiceTest {

    private RegistrationService service;

    @BeforeEach
    void setUp() {
        service = new RegistrationService();
    }

    @Test
    void testSuccessfulRegistration() throws InvalidEmailException {
        assertTrue(service.registerUser("alice@example.com", 25));
    }

    @Test
    void testBoundaryAge_exactlyMinimum() throws InvalidEmailException {
        assertTrue(service.registerUser("bob@domain.org", 18));
    }

    @Test
    void testEmailWithSubdomain() throws InvalidEmailException {
        assertTrue(service.registerUser("carol@mail.example.co.uk", 30));
    }

    @Test
    void testEmailWithPlusAlias() throws InvalidEmailException {
        assertTrue(service.registerUser("dave+newsletter@company.com", 22));
    }

    @Test
    void testNullEmail_throwsInvalidEmailException() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser(null, 25));
    }

    @Test
    void testEmptyEmail_throwsInvalidEmailException() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("", 25));
    }

    @Test
    void testBlankEmail_throwsInvalidEmailException() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("   ", 25));
    }

    @Test
    void testEmailMissingAtSymbol() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("notAnEmail.com", 25));
    }

    @Test
    void testEmailMissingDomain() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user@", 25));
    }

    @Test
    void testEmailMissingTLD() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user@domain", 25));
    }

    @Test
    void testEmailWithSpaces() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user name@example.com", 25));
    }

    @Test
    void testEmailWithDoubleAt() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user@@example.com", 25));
    }

    @Test
    void testInvalidEmailException_messageContainsBadValue() {
        String badEmail = "not-valid";
        InvalidEmailException ex = assertThrows(InvalidEmailException.class,
                () -> service.registerUser(badEmail, 25));
        assertTrue(ex.getMessage().contains(badEmail));
        assertEquals(badEmail, ex.getInvalidEmail());
    }

    @Test
    void testAge17_throwsUnderageException() {
        assertThrows(UnderageException.class,
                () -> service.registerUser("teen@example.com", 17));
    }

    @Test
    void testAge0_throwsUnderageException() {
        assertThrows(UnderageException.class,
                () -> service.registerUser("newborn@example.com", 0));
    }

    @Test
    void testNegativeAge_throwsUnderageException() {
        assertThrows(UnderageException.class,
                () -> service.registerUser("user@example.com", -5));
    }

    @Test
    void testUnderageException_messageContainsAges() {
        UnderageException ex = assertThrows(UnderageException.class,
                () -> service.registerUser("young@example.com", 16));
        assertEquals(16, ex.getApplicantAge());
        assertEquals(RegistrationService.MINIMUM_AGE, ex.getMinimumAge());
        assertTrue(ex.getMessage().contains("16"));
        assertTrue(ex.getMessage().contains(String.valueOf(RegistrationService.MINIMUM_AGE)));
    }

    @Test
    void testInvalidEmailException_isChecked() {
        assertTrue(Exception.class.isAssignableFrom(InvalidEmailException.class));
        assertFalse(RuntimeException.class.isAssignableFrom(InvalidEmailException.class));
    }

    @Test
    void testUnderageException_isUnchecked() {
        assertTrue(RuntimeException.class.isAssignableFrom(UnderageException.class));
    }

    @Test
    void testInvalidEmailTakesPriorityOverAge() {
        assertThrows(InvalidEmailException.class,
                () -> service.registerUser("bad-email", 15));
    }
}