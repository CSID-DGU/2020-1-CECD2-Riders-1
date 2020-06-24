package project.ridersserver.ridersserverapp.domain.Member;

import javax.persistence.*;

import javafx.scene.NodeBuilder;
import lombok.*;
import org.springframework.util.Assert;
import project.ridersserver.ridersserverapp.domain.Video.VideoEventEntity;
import project.ridersserver.ridersserverapp.domain.Video.VideoViewEntity;

import java.util.HashSet;
import java.util.Set;

@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(length = 20, nullable = false , unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<VideoViewEntity> viewVideos = new HashSet<>();

    public void addViewVideo(VideoViewEntity viewVideo){
        this.getViewVideos().add(viewVideo);
        viewVideo.setMember(this);
    }



}