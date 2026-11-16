package ca.daltaxi.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drivers")
@Getter @Setter @NoArgsConstructor
public class Driver {

    @Id
    @Column(name = "user_id")
    private Long userId;

    // One-to-one: the driver row shares its id with the user row
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "car_model", nullable = false)
    private String carModel;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private int seats;

    @Column(nullable = false)
    private boolean available = false;
}