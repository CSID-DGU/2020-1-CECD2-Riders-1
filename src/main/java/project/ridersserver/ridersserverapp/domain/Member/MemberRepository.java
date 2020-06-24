package project.ridersserver.ridersserverapp.domain.Member;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ridersserver.ridersserverapp.domain.Member.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String userEmail);
    
    
}
