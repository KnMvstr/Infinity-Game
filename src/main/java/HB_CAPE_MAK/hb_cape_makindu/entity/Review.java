package HB_CAPE_MAK.hb_cape_makindu.entity;


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
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

     @Column
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private float rating;

    @Column(nullable = true)
    private String image;

    @Column(name = "lastModify")
    private LocalDateTime moderatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Gamer gamer;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @LastModifiedBy
    private Moderator moderator;

//    public Date getCreatedAt() {
//        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
//    }

}

