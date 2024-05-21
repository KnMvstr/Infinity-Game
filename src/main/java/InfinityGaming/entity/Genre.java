package InfinityGaming.entity;

import InfinityGaming.entity.interfaces.NomenclatureInterface;
import InfinityGaming.entity.interfaces.SluggerInterface;
import InfinityGaming.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//Enable serialization of bidirectional relationships
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GenrePrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.GenreMinimalView.class)
    private String name;

    @JsonView(JsonViews.GenrePublicView.class)
    private String slug;

    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
    @JsonView(JsonViews.GenrePublicView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}