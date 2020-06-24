package project.ridersserver.ridersserverapp.domain.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.ridersserver.ridersserverapp.domain.Member.MemberEntity;

import java.util.Optional;

public interface VideoViewRepository extends JpaRepository<VideoViewEntity,Long> {

    Optional<VideoViewEntity> findByMemberAndVideo(MemberEntity member, VideoEntity video);

    @Modifying
    @Query(value = "UPDATE VideoViewEntity v set v.isLike = :isLike where v.member = :member and v.video = :video")
    int updateSingleVideoLike(@Param("member")MemberEntity member, @Param("video")VideoEntity video, @Param("isLike") boolean isLike);
}
