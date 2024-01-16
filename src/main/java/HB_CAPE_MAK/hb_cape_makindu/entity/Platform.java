package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.PlatformPrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.PlatformPublicView.class)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "platform_game", // nom de la table
            joinColumns = @JoinColumn(name = "platform_id"), // renomme la colonne de l'objet courant dans la table relationnelle
            inverseJoinColumns = @JoinColumn(name = "game_id") // renomme la colonne de l'objet en relation dans la table relationnelle
    )
    @JsonView(JsonViews.PlatformFullView.class)
    private List<Game> games = new ArrayList<>();
}
