package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.NomenclatureInterface;
import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.SluggerInterface;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GamePublicView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.GameMinimalView.class)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonView(JsonViews.GamePublicView.class)
    private String description;

    @Column(name = "release_date", nullable = false)
    @PastOrPresent
    @JsonView(JsonViews.GamePublicView.class)
    private LocalDate releaseDate;

    @Column(nullable = false)
    @JsonView(JsonViews.GamePublicView.class)
    private String image;

    @Column
    @JsonView(JsonViews.GamePublicView.class)
    private String backgroundImage;

    @Column
    @JsonView(JsonViews.GamePublicView.class)
    private String trailer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", nullable = false)
    @JsonView(JsonViews.GamePublicView.class)
    @JsonManagedReference
    private Genre genre;

    @JsonView(JsonViews.GamePrivateView.class)
    private String slug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", nullable = false)
    @JsonView(JsonViews.GamePublicView.class)
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_model_id", nullable = false)
    @JsonView(JsonViews.GamePublicView.class)
    @JsonBackReference
    private BusinessModel businessModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.GamePrivateView.class)
    private Moderator moderator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    @JsonView(JsonViews.GamePublicView.class)
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classification_id")
    @JsonView(JsonViews.GamePublicView.class)
    private Classification classification;

    @OneToMany(mappedBy = "game")
    @JsonView(JsonViews.GamePlusReview.class)
    @JsonManagedReference
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

