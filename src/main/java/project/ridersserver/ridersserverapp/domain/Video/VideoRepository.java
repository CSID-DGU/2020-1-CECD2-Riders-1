package project.ridersserver.ridersserverapp.domain.Video;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.ridersserver.ridersserverapp.domain.Video.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity, Long>{
	Optional<VideoEntity> findByName(String videoName);
	
	Optional<VideoEntity> deleteByName(String videoName);

	@Modifying
	@Query(value = "UPDATE VideoEntity v set v.view = :view where v.id = :videoId")
	int updateSingleVideoView(@Param("view") Long view, @Param("videoId") Long videoId);

	@Modifying
	@Query(value = "UPDATE VideoEntity v set v.like = :like where v.id = :videoId")
	int updateSingleVideoLike(@Param("like") Long like, @Param("videoId") Long videoId);

	//최신순으로 4개 가져오기
	List<VideoEntity> findTop4ByOrderByCreateTimeAtDesc();

//	//인기순으로 4개 가져오기
	List<VideoEntity> findTop4ByOrderByLikeDesc();
}
