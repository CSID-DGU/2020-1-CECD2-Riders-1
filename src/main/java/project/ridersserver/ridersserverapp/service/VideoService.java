package project.ridersserver.ridersserverapp.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import project.ridersserver.ridersserverapp.domain.Video.VideoEntity;
import project.ridersserver.ridersserverapp.domain.Video.VideoRepository;
import project.ridersserver.ridersserverapp.domain.Video.VideoViewRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class VideoService {
	private VideoRepository videoRepository;

	@Transactional
	public VideoEntity findVideoByVideoName(String videoName){
		Optional<VideoEntity> videoEntityWrapper = videoRepository.findByName(videoName);
		if(!videoEntityWrapper.isPresent()) {
			System.out.println("비디오를 찾을수 없습니다!");
			return null;
		}
		else{
			return videoEntityWrapper.get();
		}
	}

	@Transactional
	public Long SaveSingleVideo(VideoEntity videoEntity) {
        if(videoRepository.findByName(videoEntity.getName()).isPresent())
        	return new Long(-1);
        return videoRepository.save(videoEntity).getId();
	}
	
	@Transactional
	public void DeleteSingleVideo(VideoEntity videoEntity) {
        if(videoRepository.findByName(videoEntity.getName()).isPresent())
        	videoRepository.deleteByName(videoEntity.getName());
	}

	@Transactional
	public int UpSingleVideoView(VideoEntity videoEntity){
		return videoRepository.updateSingleVideoView(videoEntity.getView() + 1,videoEntity.getId());
	}

	@Transactional
	public int UpSingleVideoLike(VideoEntity videoEntity){
		return videoRepository.updateSingleVideoLike(videoEntity.getLike() + 1,videoEntity.getId());
	}

	@Transactional
	public int DownSingleVideoLike(VideoEntity videoEntity){
		return videoRepository.updateSingleVideoLike(videoEntity.getLike() - 1,videoEntity.getId());
	}

	@Transactional
	public List<String> GetLatestVideoName(){
		List<VideoEntity> allOrderByCreateTimeAt = videoRepository.findTop4ByOrderByCreateTimeAtDesc();
		List<String> fourLatestVideoName = new ArrayList<String>();
		for(int i = 0; i< allOrderByCreateTimeAt.size();i++) {
			fourLatestVideoName.add(allOrderByCreateTimeAt.get(i).getName());
		}
		return fourLatestVideoName;
	}

	@Transactional
	public List<String> GetMostLikeVideoName(){
		List<VideoEntity> allOrderByLike = videoRepository.findTop4ByOrderByLikeDesc();
		List<String> fourMostLikeVideoName = new ArrayList<String>();
		for(int i = 0; i< allOrderByLike.size();i++) {
			System.out.println(allOrderByLike.get(i).getName());
			fourMostLikeVideoName.add(allOrderByLike.get(i).getName());
		}
		return fourMostLikeVideoName;
	}
}
