package ca.daltaxi.backend.repository;

import ca.daltaxi.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDalEmail(String dalEmail);
    boolean existsByDalEmail(String dalEmail);
    boolean existsBySchoolId(String schoolId);
}