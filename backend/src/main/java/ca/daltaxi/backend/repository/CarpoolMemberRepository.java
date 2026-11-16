package ca.daltaxi.backend.repository;

import ca.daltaxi.backend.entity.CarpoolMember;
import ca.daltaxi.backend.entity.CarpoolMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolMemberRepository extends JpaRepository<CarpoolMember, CarpoolMemberId> {
}