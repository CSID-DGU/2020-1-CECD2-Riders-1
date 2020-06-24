package project.ridersserver.ridersserverapp.domain.Video;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.ridersserver.ridersserverapp.domain.Member.MemberEntity;

import javax.persistence.*;

@SequenceGenerator(name = "VIDEOVIEW_SEQ_GENERATOR", sequenceName = "VIDEOVIEW_SEQ", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
@Table(name = "videoView")
public class VideoViewEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="VIDEOVIEW_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private boolean isLike;

    @ManyToOne  //전파 속성 없고 디폴트 eager 페치
    private MemberEntity member;

    @ManyToOne
    private VideoEntity video;
}
