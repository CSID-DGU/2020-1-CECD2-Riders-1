package project.ridersserver.ridersserverapp.domain.Video;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SequenceGenerator(name = "VIDEOEVENT_SEQ_GENERATOR", sequenceName = "VIDEOEVENT_SEQ", initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
@Table(name = "videoevnet")
public class VideoEventEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="VIDEOEVENT_SEQ_GENERATOR")
    private Long id;

    private double time;

    private int whichEvent;

    @ManyToOne
    private VideoEntity video;
}
