package HB_CAPE_MAK.hb_cape_makindu.entity;

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
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "platform_game", // nom de la table
            joinColumns = @JoinColumn(name = "platform_id"), // renomme la colonne de l'objet courant dans la table relationnelle
            inverseJoinColumns = @JoinColumn(name = "game_id") // renomme la colonne de l'objet en relation dans la table relationnelle
    )
    private List<Game> games = new ArrayList<>();
}
