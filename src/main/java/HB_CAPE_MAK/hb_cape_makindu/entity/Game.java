package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.NomenclatureInterface;
import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.SluggerInterface;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_date", nullable = false)
    @PastOrPresent
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String image;

    @Column
    private String backgroundImage;

    @Column
    private String trailer;


    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    
    private String slug;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "business_Model_id", nullable = false)
    private BusinessModel businessModel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Moderator moderator;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private Classification classification;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }

    public void addPlatform(Platform platform) {
        if (!platforms.contains(platform)) {
            platforms.add(platform);
        }
    }


    //Pas de relation géré avec un modérateur
}

