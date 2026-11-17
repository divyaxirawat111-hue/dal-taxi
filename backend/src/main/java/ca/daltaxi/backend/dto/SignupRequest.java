package ca.daltaxi.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequest(
    @NotBlank String name,
    @NotBlank @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@dal\\.ca$", message = "must be a @dal.ca email")
    String dalEmail,
    @NotBlank
    @Pattern(regexp = "^B00\\d{6}$", message = "must be B00 followed by 6 digits")
    String schoolId,
    @NotBlank @Size(min = 8, message = "must be at least 8 characters")
    String password
) {}