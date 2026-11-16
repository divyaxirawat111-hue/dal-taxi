package ca.daltaxi.backend.repository;

import ca.daltaxi.backend.entity.CarpoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolGroupRepository extends JpaRepository<CarpoolGroup, Long> {
}