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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classification implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ClassificationPrivateView.class)
    private Long id;

    @JsonView(JsonViews.ClassificationMinimalView.class)
    private String name;

    @JsonView(JsonViews.ClassificationPublicView.class)
    private String description;

    @JsonView(JsonViews.ClassificationFullView.class)
    private String image;

    @JsonView(JsonViews.ClassificationPrivateView.class)
    private String slug;

    @OneToMany (mappedBy = "classification")
    @JsonView(JsonViews.ClassificationPublicView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}