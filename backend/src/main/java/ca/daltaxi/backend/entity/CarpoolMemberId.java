package ca.daltaxi.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class CarpoolMemberId implements Serializable {

    @Column(name = "carpool_id")
    private Long carpoolId;

    @Column(name = "rider_id")
    private Long riderId;
}