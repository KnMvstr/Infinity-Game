package InfinityGaming.entity;

import InfinityGaming.entity.interfaces.NomenclatureInterface;
import InfinityGaming.entity.interfaces.SluggerInterface;

import InfinityGaming.json_views.JsonViews;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

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
public class Publisher implements
        SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.PublisherPrivateView.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(JsonViews.PublisherMinimalView.class)
    private String name;

    @JsonView(JsonViews.PublisherPublicView.class)
    private String slug;

    @OneToMany(mappedBy = "publisher")
    @JsonView(JsonViews.PublisherPublicView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}