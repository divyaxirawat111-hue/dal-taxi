package ca.daltaxi.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "dal_email", nullable = false, unique = true)
    private String dalEmail;

    @Column(name = "school_id", nullable = false, unique = true)
    private String schoolId;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private boolean verified = false;

    @Column(name = "is_admin", nullable = false)
    private boolean admin = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_role", nullable = false)
    private Role activeRole = Role.RIDER;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}