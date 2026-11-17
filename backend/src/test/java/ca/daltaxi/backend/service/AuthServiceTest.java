package ca.daltaxi.backend.service;

import ca.daltaxi.backend.dto.SignupRequest;
import ca.daltaxi.backend.dto.UserResponse;
import ca.daltaxi.backend.entity.User;
import ca.daltaxi.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock UserRepository users;
    @Mock PasswordEncoder encoder;
    @InjectMocks AuthService service;

    private final SignupRequest valid =
        new SignupRequest("Divyaxi", "divyaxi@dal.ca", "B00123456", "password123");

    @Test
    void signupHashesPasswordAndSaves() {
        when(users.existsByDalEmail(any())).thenReturn(false);
        when(users.existsBySchoolId(any())).thenReturn(false);
        when(encoder.encode("password123")).thenReturn("hashed");
        when(users.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserResponse result = service.signup(valid);

        assertEquals(1L, result.id());
        assertFalse(result.verified());
        verify(users).save(argThat(u -> u.getPasswordHash().equals("hashed")));
    }

    @Test
    void signupRejectsDuplicateEmail() {
        when(users.existsByDalEmail("divyaxi@dal.ca")).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> service.signup(valid));
        verify(users, never()).save(any());
    }
}