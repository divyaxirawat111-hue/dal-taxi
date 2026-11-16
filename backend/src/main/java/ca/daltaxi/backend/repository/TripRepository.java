package ca.daltaxi.backend.repository;

import ca.daltaxi.backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByRiderIdOrderByRequestedAtDesc(Long riderId);
}