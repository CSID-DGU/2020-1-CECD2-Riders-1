package project.ridersserver.ridersserverapp.domain.Video;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoEventRepository extends JpaRepository<VideoEventEntity,Long> {

    List<VideoEventEntity> findByWhichEventAndVideoOrderByTimeDesc(int whichEvent, VideoEntity video);
}
