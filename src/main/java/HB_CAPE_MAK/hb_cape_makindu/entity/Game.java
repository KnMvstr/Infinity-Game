package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.SluggerInterface;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
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
public class Game implements SluggerInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GamePrivateView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.GamePublicView.class)
    private String name;

    @JsonView(JsonViews.GamePublicView.class)
    private String description;

    @Column(name = "release_date")
    @PastOrPresent
    @JsonView(JsonViews.GamePublicView.class)
    private LocalDate releaseDate;

    @JsonView(JsonViews.GamePublicView.class)
    private String image;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonView(JsonViews.GamePublicView.class)
    private Genre genre;

    @JsonView(JsonViews.GamePrivateView.class)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonView(JsonViews.GamePublicView.class)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "business_Model_id")
    @JsonView(JsonViews.GamePublicView.class)
    private BusinessModel businessModel;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    @JsonView(JsonViews.GameFullView.class)
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "classification_id")
    @JsonView(JsonViews.GamePublicView.class)
    private Classification classification;

    @OneToMany(mappedBy = "game")
    @JsonView(JsonViews.GamePublicView.class)
    private List<Review> reviews = new ArrayList<>();
    @Override
    public String getField() {
        return name;
    }

    //Pas de relation géré avec un modérateur
}

