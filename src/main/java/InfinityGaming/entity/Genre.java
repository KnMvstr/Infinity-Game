package InfinityGaming.entity;

import InfinityGaming.entity.interfaces.NomenclatureInterface;
import InfinityGaming.entity.interfaces.SluggerInterface;
import InfinityGaming.json_views.JsonViews;
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
public class Genre implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GenrePrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.GenrePublicView.class)
    private String name;

    @JsonView(JsonViews.GenrePublicView.class)
    private String slug;

    @OneToMany(mappedBy = "genre")
    @JsonView(JsonViews.GenrePublicView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}

