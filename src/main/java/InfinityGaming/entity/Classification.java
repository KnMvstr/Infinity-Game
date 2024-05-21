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
public class Classification implements SluggerInterface,
        NomenclatureInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ClassificationPrivateView.class)
    private Long id;

    @JsonView(JsonViews.ClassificationPublicView.class)
    private String name;

    @JsonView(JsonViews.ClassificationPublicView.class)
    private String description;

    @JsonView(JsonViews.ClassificationPublicView.class)
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
