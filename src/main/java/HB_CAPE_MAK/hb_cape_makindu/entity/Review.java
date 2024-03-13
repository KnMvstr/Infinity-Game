package HB_CAPE_MAK.hb_cape_makindu.entity;


import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import java.time.LocalDateTime;


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

    @Column
    @JsonView(JsonViews.ReviewPublicView.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonView(JsonViews.ReviewPublicView.class)
    private float rating;

    @Column(nullable = true)
    @JsonView(JsonViews.ReviewPublicView.class)
    private String image;

    @Column(name = "lastModify")
    @JsonView(JsonViews.ReviewPrivateView.class)
    private LocalDateTime moderatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ReviewPublicView.class)
    @JsonManagedReference
    private Gamer gamer;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @JsonView(JsonViews.ReviewPublicView.class)
    private Game game;

    @ManyToOne
    @LastModifiedBy
    @JsonView(JsonViews.ReviewPrivateView.class)
    private Moderator moderator;

//    public Date getCreatedAt() {
//        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
//    }
}

