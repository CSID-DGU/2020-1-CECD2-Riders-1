package project.ridersserver.ridersserverapp.domain.Video;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SequenceGenerator(name = "VIDEO_SEQ_GENERATOR", sequenceName = "VIDEO_SEQ", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
@Table(name = "video")
public class VideoEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE , generator="VIDEO_SEQ_GENERATOR")
	private Long id;

	@Column(name = "videoname",length = 100, nullable = false , unique = true)
	private String name;

	@Column(name = "videolike",nullable = false)
	private Long like;

	@Column(name = "videoview", nullable = false)//조회수
	private Long view;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createTimeAt;

	@Column(name = "uploaderName",length = 100, nullable = false)
	private String uploaderName;

	@Column(name = "hometeam",length = 100, nullable = false)
	private String homeTeam;

	@Column(name = "awayteam",length = 100, nullable = false)
	private String awayTeam;

	//, cascade = CascadeType.PERSIST : 이러면 컬랙션에 들어있는 모든 데이터들과 해당 객체가 연동된다(해당 객체를 저장 및 삭제하면 연동된 객체들도 변함)
	//여기선 videoEntity => 나머지로 전파되도록 함
	// 기본적으로 OneToMany는 lazy방식 + ManyToOne은 eger방식

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
	private Set<VideoEventEntity> events = new HashSet<>();

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
	private Set<VideoViewEntity> viewMembers = new HashSet<>();

	public void addEvent(VideoEventEntity event){
		this.getEvents().add(event);
		event.setVideo(this);
	}

	public void addViewMember(VideoViewEntity viewMember){
		this.getViewMembers().add(viewMember);
		viewMember.setVideo(this);
	}




}