package HB_CAPE_MAK.hb_cape_makindu.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(length = 255, nullable = false)
    private String description;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private float note;

    @Column(name = "lastModify")
    private LocalDateTime lastModify;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false, unique = true)
    private Moderator moderator;

}

