package ca.daltaxi.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "carpool_members")
@Getter @Setter @NoArgsConstructor
public class CarpoolMember {

    @EmbeddedId
    private CarpoolMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carpoolId")
    @JoinColumn(name = "carpool_id")
    private CarpoolGroup carpool;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("riderId")
    @JoinColumn(name = "rider_id")
    private User rider;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();
}