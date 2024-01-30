package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ReviewPrivateView.class)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    @JsonView(JsonViews.ReviewPublicView.class)
    private String description;

    @CreatedDate
    @Timestamp
    @Column
    @JsonView(JsonViews.ReviewPrivateView.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonView(JsonViews.ReviewPublicView.class)
    private float rating;

    @Column (nullable = true)
    @JsonView(JsonViews.ReviewPublicView.class)
    private String image;

    @LastModifiedDate
    @JsonView(JsonViews.ReviewPublicView.class)
    @Column(name = "lastModify")
    private LocalDateTime moderatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Gamer gamer;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @JsonView(JsonViews.ReviewPublicView.class)
    private Game game;

    @ManyToOne
    @LastModifiedBy
    @JsonView(JsonViews.ReviewFullView.class)
    private Moderator moderator;

//    public Date getCreatedAt() {
//        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
//    }

}

