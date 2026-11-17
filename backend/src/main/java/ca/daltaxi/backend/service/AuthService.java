package ca.daltaxi.backend.service;

import ca.daltaxi.backend.dto.SignupRequest;
import ca.daltaxi.backend.dto.UserResponse;
import ca.daltaxi.backend.entity.User;
import ca.daltaxi.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    public UserResponse signup(SignupRequest req) {
        if (users.existsByDalEmail(req.dalEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        if (users.existsBySchoolId(req.schoolId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "School ID already registered");
        }
        User user = new User();
        user.setName(req.name());
        user.setDalEmail(req.dalEmail());
        user.setSchoolId(req.schoolId());
        user.setPasswordHash(encoder.encode(req.password()));
        User saved = users.save(user);
        return new UserResponse(saved.getId(), saved.getName(), saved.getDalEmail(),
                saved.isVerified(), saved.getActiveRole().name());
    }
}