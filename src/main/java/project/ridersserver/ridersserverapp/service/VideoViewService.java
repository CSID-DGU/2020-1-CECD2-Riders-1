package project.ridersserver.ridersserverapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ridersserver.ridersserverapp.domain.Member.MemberEntity;
import project.ridersserver.ridersserverapp.domain.Video.VideoEntity;
import project.ridersserver.ridersserverapp.domain.Video.VideoViewEntity;
import project.ridersserver.ridersserverapp.domain.Video.VideoViewRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VideoViewService {

    @Autowired
    private VideoViewRepository videoViewRepository;

    @Transactional
    public VideoViewEntity findByMemberAndVideo(MemberEntity member, VideoEntity video){
        Optional<VideoViewEntity> byMemberAndVideo = videoViewRepository.findByMemberAndVideo(member, video);
        if(byMemberAndVideo.isPresent())
            return byMemberAndVideo.get();
        else
            return null;
    }

    @Transactional
    public int updateSingleVideoLike(VideoViewEntity videoViewEntity, boolean isLike){
        return videoViewRepository.updateSingleVideoLike(videoViewEntity.getMember(),videoViewEntity.getVideo(),isLike);
    }

    @Transactional
    public Long saveSingleVideoView(VideoViewEntity videoViewEntity){
        if(videoViewRepository.findByMemberAndVideo(videoViewEntity.getMember(),videoViewEntity.getVideo()).isPresent()){
            System.out.println("이미 존재하는 조회관계!");
            return new Long(-1);
        }
        else
            return videoViewRepository.save(videoViewEntity).getId();
    }
}
