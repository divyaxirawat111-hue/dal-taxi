package ca.daltaxi.backend.service;

import ca.daltaxi.backend.dto.SignupRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void acceptsValidStudent() {
        var req = new SignupRequest("Divyaxi", "divyaxi@dal.ca", "B00123456", "password123");
        assertTrue(validator.validate(req).isEmpty());
    }

    @Test
    void rejectsNonDalEmail() {
        var req = new SignupRequest("Divyaxi", "divyaxi@gmail.com", "B00123456", "password123");
        assertFalse(validator.validate(req).isEmpty());
    }

    @Test
    void rejectsBadSchoolId() {
        var req = new SignupRequest("Divyaxi", "divyaxi@dal.ca", "12345", "password123");
        assertFalse(validator.validate(req).isEmpty());
    }
}